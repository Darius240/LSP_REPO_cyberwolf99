Reflection — Assignment 3
Differences in Design

Assignment 2 (A2) was a procedural ETL pipeline: all logic—reading CSVs, transforming data, applying discounts, categorizing prices, and writing output—was implemented in a single ETLPipeline class. Data was handled as raw String[] arrays, making transformations tightly coupled and harder to extend.

Assignment 3 (A3) introduced an object-oriented (OO) redesign. Key changes include:

Product class: encapsulates product attributes (id, name, price, category, priceRange) and provides a typed representation of a row.

CSVReader class: handles reading CSV files and converting each row into a Product object.

ProductTransformer class (implements Transformer interface): applies all transformation logic (uppercase names, discounts, category recategorization, price ranges) to Product objects.

CSVWriter class: writes a list of Product objects to the output CSV with proper formatting.

This separation allows each class to focus on a single responsibility, making the pipeline easier to test, maintain, and extend.

OO Concepts Used

Class & Object: Product, CSVReader, CSVWriter, ProductTransformer are classes; each product in the dataset is an object of Product.

Encapsulation: Product attributes are private with public getters/setters, preventing external code from directly modifying internal state.

Inheritance & Polymorphism: ProductTransformer implements the Transformer interface. This allows polymorphic behavior: any class implementing Transformer can be swapped in without changing the ETL pipeline.

List of Objects: The ETL pipeline uses List<Product> instead of List<String[]>, improving type safety and clarity when applying transformations.

Testing to Confirm Parity with A2

To ensure A3 matched A2 behavior:

Sample inputs: Used the same products.csv as A2, including a mix of electronics, low-priced items, and premium products.

Edge cases:

Missing or empty files → confirmed CSVReader throws appropriate IOException or returns only header row.

Malformed rows → skipped, as in A2.

Products on discount threshold (e.g., $500 electronics) → verified correct category and price range assignment.

Diff method: After running both A2 and A3 pipelines, I compared transformed_products.csv from each using a file diff (diff A2_output.csv A3_output.csv). All rows matched, confirming parity.

How I Used Generative AI

I used AI to help refactor A2 procedural code into A3’s OO design. Prompts included requests to:

Suggest classes for ETL separation

Convert row-based processing into object-based transformations

Implement reading and writing CSV files using typed objects

I then edited the AI-generated code to:

Align with assignment requirements (e.g., output paths, rounding rules, discount logic)

Add explicit exception handling (InvalidDataException, IOException)

Maintain parity with A2 for testing purposes

This workflow helped speed up the design phase while ensuring correctness and maintainability.
