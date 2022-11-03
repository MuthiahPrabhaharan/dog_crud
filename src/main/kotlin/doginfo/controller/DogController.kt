package doginfo.controller
import com.google.gson.GsonBuilder
import doginfo.model.Dog
import doginfo.dao.DogDao
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import jakarta.inject.Inject

@Controller("/dog")
class DogController(

    @Inject private val dogDao: DogDao
)
{
    companion object{
        private val mapper = GsonBuilder().setPrettyPrinting().create()
    }

    @Post("/add")
    fun addDog(@Body dog: Dog): HttpResponse<String> {
        dogDao.save(dog)
        return HttpResponse.ok("Saved Dog Id is ${dog.id} ok!")
    }

    @Put("/update")
    fun updateDog(@Body dog: Dog): HttpResponse<String> {
        dogDao.update(dog)
        return HttpResponse.ok("Modified Dog Id is ${dog.id} ok!")
    }

    @Delete("/remove")
    fun deleteDog(@Body dog: Dog): HttpResponse<String> {
        dogDao.delete(dog)
        return HttpResponse.ok("Removed Dog Id is ${dog.id} ok!")
    }

    @Get("/fetch/{id}")
    fun getDogById(@PathVariable("id") dogId: String): HttpResponse<Dog> {
        val dog = dogDao.getById(Dog(dogId))
        return HttpResponse.ok(dog)
    }

    @Get("/all")
    fun getAllDogs(): HttpResponse<String> {
        val dogList = dogDao.getAll()
        val jsonBody = mapper.toJson(dogList)
        return HttpResponse.ok(jsonBody)
    }

//    @Get("/pay")
//    fun getPayment(): HttpResponse<String> {
//       return HttpResponse.ok("You are re-directed towards payment section please wait!")
//    }
}
