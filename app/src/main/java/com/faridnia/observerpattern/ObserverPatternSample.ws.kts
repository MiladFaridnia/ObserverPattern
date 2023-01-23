package com.faridnia.observerpattern


open class Subject {
    private var observers = mutableListOf<Observer>()

    fun callObservers() {
        for(obs in observers) obs.update()
    }

    fun attach(obs : Observer) {
        observers.add(obs)
    }

    fun detach(obs : Observer) {
        observers.remove(obs)
    }
}

interface Observer {
    fun update()
}

class Sensor : Subject() {
    var temperature: Int = 0
        set(value) {
            field = value
            callObservers()
        }
}

class Monitor(val sensor: Sensor) : Observer {
    init {
        sensor.attach(this)
    }

    override fun update() {
        val newTemperature = sensor.temperature
        println("Monitor updated with temp :$newTemperature")
    }

}

fun main(temp: Int) {

    val sensor = Sensor()
    val monitor = Monitor(sensor)

    monitor.sensor.temperature = temp


}

main(5)
main(10)
main(20)
