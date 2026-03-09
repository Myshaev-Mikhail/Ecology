package com.example.ecology.data.local

import com.example.ecology.domain.Report
import com.example.ecology.domain.User

fun Report.toEntity() = ReportEntity(
    id = id,
    userId = userId,
    district = district,
    street = street,
    house = house,
    description = description,
    photo = photo
)

fun User.toEntity() = UserEntity(
    id = id,
    role = role,
    nickname = nickname,
    email = email,
    password = password,
    isSubscription = isSubscription
)

fun ReportEntity.toDomain() = Report(
    id = id,
    userId = userId,
    district = district,
    street = street,
    house = house,
    description = description,
    photo = photo
)

fun UserEntity.toDomain() = User(
    id = id,
    role = role,
    nickname = nickname,
    email = email,
    password = password,
    isSubscription = isSubscription
)