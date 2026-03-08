package com.example.ecology.data.local

import com.example.ecology.domain.Report
import com.example.ecology.domain.User

fun Report.toEntity() = ReportEntity(
    district = district,
    street = street,
    house = house,
    description = description,
    photo = photo
)

fun User.toEntity() = UserEntity(
    nickname = nickname,
    email = email,
    password = password,
    isSubscription = isSubscription
)