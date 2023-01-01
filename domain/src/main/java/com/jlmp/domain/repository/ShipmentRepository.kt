package com.jlmp.domain.repository

import com.jlmp.domain.Shipment

interface ShipmentRepository {
    suspend fun getAll(): List<Shipment>
}