package com.swcontest.somding.common.config.s3

import com.swcontest.somding.exception.s3.S3ErrorCode
import com.swcontest.somding.exception.s3.S3Exception
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.View
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.GetUrlRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest

import java.io.IOException
import java.net.URL
import java.util.*

@Component
class S3Manager(
        private val s3Client: S3Client,
        private val amazonConfig: S3Config,
        private val error: View
) {

    // 단일 파일 업로드
    fun uploadFile(keyName: String, file: MultipartFile): String {
        val originalFilename = file.originalFilename ?: ""
        val extension = if (originalFilename.contains(".")) {
            originalFilename.substring(originalFilename.lastIndexOf("."))
        } else {
            ""
        }

        val putObjectRequest = PutObjectRequest.builder()
                .bucket(amazonConfig.getBucket())
                .key(keyName + extension)
                .contentType(file.contentType)
                .build()
        return try {
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.inputStream, file.size))
            val url = s3Client.utilities().getUrl { b: GetUrlRequest.Builder ->
                b.bucket(amazonConfig.getBucket()).key(keyName + extension)
            }
            url.toString()
        } catch (e: IOException) {
            throw S3Exception(S3ErrorCode.UPLOAD_FAILED)
        }
    }

    // 다중 파일 업로드
    fun uploadFiles(keyNames: List<String>, files: List<MultipartFile>): List<String> {
        if (files.size != keyNames.size) {
            throw S3Exception(S3ErrorCode.SIZE_MISMATCH)
        }
        return files.indices.map { i -> uploadFile(keyNames[i], files[i]) }
    }

    // 단일 파일 삭제
    fun deleteFile(fileUrl: String) {
        try {
            val url = URL(fileUrl)
            val bucket = url.host.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            val key = url.path.substring(1)

            val deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build()
            s3Client.deleteObject(deleteObjectRequest)
        } catch (e: IOException) {
            throw S3Exception(S3ErrorCode.IMAGE_NOT_FOUND)
        }
    }

    // 다중 파일 삭제
    fun deleteFiles(fileUrls: List<String>) {
        fileUrls.forEach { fileUrl -> deleteFile(fileUrl) }
    }

    // KeyName을 만들어서 리턴 해주는 메서드 - 파일 이름이 중복되지 않게 경로와 uuid 값 연결

//     profile 디렉토리
    fun generateProfileKeyName(uuid: UUID): String {
        return "${amazonConfig.profilePath}/${uuid}"
    }
//
    // project 디렉토리
    fun generateProjectKeyName(uuid: UUID): String {
        return "${amazonConfig.projectPath}/${uuid}"
    }
}
