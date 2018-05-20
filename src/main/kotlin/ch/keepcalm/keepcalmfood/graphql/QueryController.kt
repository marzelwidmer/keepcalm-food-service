package ch.keepcalm.keepcalmfood.graphql

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/query")
class QueryController {

    @PostMapping
    fun query(@RequestBody query: String) = ResponseEntity.ok("Hello $query from GraphQL")

}