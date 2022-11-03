package doginfo.model
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
data class Dog(

    @get:DynamoDbPartitionKey
    var id: String? = null,
    var name: String? = null,
    var age: String? = null
)
