package fr.isen.leclech.androiderestaurant.Network

import java.io.Serializable

class RegisterResult(val data: User)

class User(val id: Int): Serializable