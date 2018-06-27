package ch.keepcalm.keepcalmfood.graphql

import ch.keepcalm.keepcalmfood.graphql.person.Person
import graphql.GraphQL
import graphql.schema.DataFetcher
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import org.springframework.core.io.ClassPathResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.annotation.PostConstruct


@RestController
@RequestMapping("/query")
class QueryController( val allPersonDataFetcher: DataFetcher<Person>) {

   /* @PostConstruct
    fun loadSchema() {
        val fileReader = BufferedReader(InputStreamReader(ClassPathResource("person.graphqls").inputStream, "UTF-8"))
        val typeRegistry = SchemaParser().parse(fileReader)
        val wiring = buildRuntimeWiring()
        val schema = SchemaGenerator().makeExecutableSchema(typeRegistry, wiring)
        val graphQL = newGraphQL(schema).build()

    }

    private fun buildRuntimeWiring() : RuntimeWiring =
            newRuntimeWiring()
//                    .type("Query", .dataFetcher("AllPersons", allPersonDataFetcher)) )
                    .build()
*/

    lateinit var graphQL: GraphQL

    // load schema at application start up
    @PostConstruct
    @Throws(IOException::class)
    fun loadSchema() {
        // get the schema
        val schemaFile = BufferedReader(InputStreamReader(ClassPathResource("graphql/person.graphqls").inputStream, "UTF-8"))
        // parse schema
        val typeRegistry = SchemaParser().parse(schemaFile)
        val wiring = buildRuntimeWiring()
        val schema = SchemaGenerator().makeExecutableSchema(typeRegistry, wiring)
        graphQL = GraphQL.newGraphQL(schema).build()
    }

    private fun buildRuntimeWiring(): RuntimeWiring {
        /*
         * This dataFetcher first argument i.e "allMovies" or "movie" this name
         * should be same with the field which u declare in your movie.graphqls
         * in typeQuery section and one more things these 2 field name should be
         * same which we are sending as part of request query from postman for
         * Example : { allMovies{pass required field } }
         */
        return RuntimeWiring.newRuntimeWiring().type("Query") { typeWiring ->
            typeWiring
                    .dataFetcher("allPerson", allPersonDataFetcher)
//                    .dataFetcher("person", personDataFetcher)
        }.build()
    }


    @PostMapping
    fun query(@RequestBody query: String): ResponseEntity<String> {

        return ResponseEntity.ok("Hello from GraphQL")
    }

}
//
//class Query( val personRepository: PersonRepository) : GraphQLQueryResolver {
//
//    fun findAllBooks(): Iterable<Person> {
//        return personRepository.findAll()
//    }
//}