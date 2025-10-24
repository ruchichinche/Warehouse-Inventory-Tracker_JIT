# Warehouse Inventory Tracker (Core Java)

This small project demonstrates an event-driven warehouse inventory tracker using core Java (no external libraries).

Structure
- src/main/java/com/warehouse/inventory - source files

How to compile and run (PowerShell)

1) Compile:

```powershell
# From the repository root
javac -d out src\main\java\com\warehouse\inventory\exceptions\*.java src\main\java\com\warehouse\inventory\*.java
```

2) Run the demo:

```powershell
java -cp out com.warehouse.inventory.Main
```

Example workflow implemented in `Main`:
- Adds product `Laptop` with threshold 5
- Receives shipment of 10
- Fulfills 6 orders (remaining 4)
- Console alert is printed when stock drops below threshold

Notes
- All operations are through methods on `Warehouse` and `Product`.
- Uses in-memory collections (ConcurrentHashMap, CopyOnWriteArrayList).
- Thread-safe basic operations to allow concurrent updates.
