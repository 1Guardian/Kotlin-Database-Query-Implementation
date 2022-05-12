package com.example.friendlist

data class PopPeople(
    val results: List<PopPeopleResult>
)

data class PopPeopleResult(
    val name: String,
    val profile_path: String,
    val popularity: Double
)