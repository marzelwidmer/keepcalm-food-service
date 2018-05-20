package ch.keepcalm.keepcalmfood.graphql.person

import org.springframework.data.annotation.TypeAlias
import java.util.*

@TypeAlias("person") // MongoDb _class name without package
data class Person(
        var id: String = UUID.randomUUID().toString(),
        var firstName: String,
        var lastName: String,
        var age: Int
)