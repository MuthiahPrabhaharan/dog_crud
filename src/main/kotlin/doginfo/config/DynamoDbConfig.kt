package doginfo.config
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import jakarta.inject.Singleton
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

@Factory
class DynamoDbConfig {

    @Singleton
    fun dynamoDBClient(): DynamoDbClient = DynamoDbClient.builder().build()

    @Bean
    @Singleton
    fun dynamoDBEnhancedClient(): DynamoDbEnhancedClient {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDBClient()).build()
    }
}
