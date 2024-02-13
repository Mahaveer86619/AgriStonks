package com.se7en.agristonks.uility

enum class UserType(val typeName: String, val code: String) {
    FARMER(
        typeName = "Farmer",
        code = "ROLE_FARMER"
    ),
    WAREHOUSE(
        typeName = "Warehouse Provider",
        code = "ROLE_WAREHOUSE"
    ),
    SERVICE_PROVIDER(
        typeName = "Service Provider",
        code = "ROLE_SERVICE"
    ),
    COMPANY(
        typeName = "Company",
        code = "ROLE_COMPANY"
    )
}