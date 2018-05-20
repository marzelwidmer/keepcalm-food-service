package ch.keepcalm.keepcalmfood.graphql.person

 import org.springframework.data.mongodb.repository.MongoRepository


interface PersonRepository : MongoRepository<Person, String> {

}
