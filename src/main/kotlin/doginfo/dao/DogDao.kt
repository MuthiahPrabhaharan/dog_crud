package doginfo.dao
import doginfo.model.Dog
import jakarta.inject.Inject
import jakarta.inject.Singleton
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable

@Singleton
class DogDao(

    @Inject private val dynamoClient: DynamoDbEnhancedClient
)
{
    private val dogTable = dynamoClient.table("dogies", TableSchema.fromBean(Dog::class.java))

    fun save(dog: Dog){
        return dogTable.putItem(dog)
    }

    fun update(dog: Dog): Dog{
        return dogTable.updateItem(dog)
    }

    fun delete(dog: Dog): Dog{
        return dogTable.deleteItem(dog)
    }

    fun getById(dog: Dog): Dog{
        return dogTable.getItem(dog)
    }

    fun getAll(): MutableList<Dog>{
        val dogList: PageIterable<Dog> = dogTable.scan()
        val dogWholeList = mutableListOf<Dog>()
        dogList.stream().forEach{
                fetch -> fetch.items().forEach {
                dog -> dogWholeList.add(dog)
        }
        }
        return dogWholeList
    }
}
