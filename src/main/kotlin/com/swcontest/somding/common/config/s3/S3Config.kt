package com.swcontest.somding.common.config.s3

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@Configuration
class S3Config {

    @Value("\${cloud.aws.credentials.access-key}")
    private val accessKey: String? = null

    @Value("\${cloud.aws.credentials.secret-key}")
    private val secretKey: String? = null

    @Value("\${cloud.aws.region.static}")
    private val region: String? = null

    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    @Value("\${cloud.aws.s3.path.profilePath}")
    lateinit var profilePath: String

    @Value("\${cloud.aws.s3.path.projectPath}")
     lateinit var projectPath: String

//    @Value("\${aws.s3.path.commentPath}")
//    private lateinit var commentPath: String

    fun getBucket(): String {
        return bucket
    }
//    fun getBoardPath(): String = boardPath
//    fun getCommentPath(): String = commentPath

    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
                .credentialsProvider { awsCredentials() }
                .region(Region.of(region))
                .build()
    }

    private fun awsCredentials(): AwsCredentials {
        return object : AwsCredentials {
            override fun accessKeyId(): String? {
                return accessKey
            }

            override fun secretAccessKey(): String? {
                return secretKey
            }
        }
    }
}
