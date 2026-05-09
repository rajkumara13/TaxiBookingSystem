🚕 Taxi Booking System (Java Console Project)
📌 Project Overview
This is a simple Java console-based Taxi Booking System.
The system automatically assigns taxis to customers based on availability, distance, and earnings.

It is designed to practice OOP concepts like:

Classes & Objects

Encapsulation

Lists (ArrayList)

Basic logic building

🧩 How the System Works

Customers request a taxi by giving:

Pickup point (A–F)

Drop point

Pickup time

System checks all taxis and selects:

Taxis that are free at the pickup time

Taxi closest to the pickup point

If multiple taxis are same distance, the one with less earnings is chosen

Booking is created and assigned to the selected taxi

Taxi details are updated:

Current location

Free time

Total earnings

Final report is displayed with:

Taxi earnings

All bookings handled by each taxi

🧱 Classes Used
👤 Customer

Stores customer details:

Customer ID

Pickup location

Drop location

Pickup time

📄 Booking

Stores booking details:

Booking ID

Drop time

Charge amount

Customer information

🚖 Taxi

Represents a taxi:

Taxi ID

Current location

Free time

Total earnings

List of bookings

⚙️ BookingSystem

Main logic controller:

Creates taxis

Finds suitable taxi

Calculates charges

Assigns bookings

Displays booking history

▶️ Main

Creates customers

Books taxis

Displays final output

💰 Fare Calculation Logic

Base fare: ₹100

Distance calculated using pickup & drop points

First 5 km free

Remaining distance charged at ₹10 per km

📊 Output Details

After execution, the system displays:

Taxi ID

Total earnings

All bookings with:

Booking ID

Customer ID

Pickup & Drop points

Pickup time & Drop time

Total charge

🎯 Purpose of This Project

Understand real-world problem solving

Practice Java OOP concepts

Learn object interaction & decision logic

Good beginner-level mini project for interviews
