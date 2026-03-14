# JWK Vehicle Sharing

JWK Vehicle Sharing is a comprehensive desktop application for managing a vehicle sharing system. It allows users to rent various types of vehicles, administrators to manage the fleet and workers, and service workers to handle vehicle maintenance.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Architecture & Design Patterns](#architecture--design-patterns)
  - [OOP Fundamentals](#oop-fundamentals)
  - [Strategy Pattern](#strategy-pattern)
  - [Observer Pattern](#observer-pattern)

## Features
- **User Management**: Registration and login for different roles (Customer, Administrator, Service Worker).
- **Vehicle Fleet**: Support for multiple vehicle types: Cars, Motorcycles, Trucks (TIR), Electric Scooters, and Bicycles.
- **Rental System**: Daily and long-term rental strategies with automated cost calculation.
- **Maintenance**: Service workers can handle repair orders and vehicle status updates.
- **Real-time Statistics**: Live monitoring of available vehicles by type.
- **Data Persistence**: Serialization of rentals, vehicles, and user data.

## Technologies
- **Java**
- **Swing**: For the Graphical User Interface (GUI).
- **Maven**: Project management and build automation.
- **Gson**: JSON handling for specific data structures.

## Architecture & Design Patterns

The project is built with a strong focus on clean code and software design principles.

### OOP Fundamentals
- **Inheritance**: A clear hierarchy for vehicles (`Pojazd` base class, `PojazdSilnikowy` for motorized vehicles, and specific implementations like `SamochodOsobowy`, `Motocykl`, etc.). Also used for people (`Osoba` abstract class, `Klient`, `Pracownik`, etc.).
- **Polymorphism**: Used throughout the rental and vehicle systems, allowing the system to treat different vehicle types and strategies uniformly.
- **Encapsulation**: Strict use of private fields and public accessors, with logic organized into Services and Repositories to separate concerns.
- **Abstraction**: Interfaces and abstract classes define common behaviors for entities, ensuring a extensible and maintainable codebase.

### Strategy Pattern
The **Strategy Pattern** is implemented to handle different pricing models for rentals:
- `StrategiaCenowa`: The interface defining the common method `wyliczKoszt`.
- `StrategiaDobowa`: Implementation for daily rentals.
- `StrategiaDlugoterminowa`: Implementation for long-term rentals with different rate calculations.
This allows the system to switch pricing logic dynamically based on the user's choice.

### Observer Pattern
The **Observer Pattern** is utilized for real-time updates of the vehicle fleet statistics:
- `Observer`: Interface for receiving updates.
- `StatsControler`: Implements the observer interface and maintains live counts of available vehicles.
Whenever a vehicle is added, removed, or its status changes, the `StatsControler` is notified and updates the UI accordingly, ensuring consistent data across different panels.

