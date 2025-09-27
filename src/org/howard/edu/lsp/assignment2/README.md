# Assignment 2 – CSV ETL Pipeline in Java

## Overview
This project implements a simple **Extract–Transform–Load (ETL) pipeline** in Java.  
It reads product data from a CSV file, applies transformations to specific columns, and writes the transformed data back to a new CSV file.

- **Input:** `data/products.csv`  
- **Output:** `data/transformed_products.csv`

## Assumptions
- Input file always contains a header row (`ProductID,Name,Price,Category`).  
- Fields contain no commas or quotes.  
- Rows with missing or malformed data are skipped.  
- Discount and recategorization rules apply only if the **original category** is `"Electronics"`.  
- Rounding is required to **two decimals using half-up rounding**.  

## Design Notes
- The program is structured into three main methods:
  - `extract()` → Reads CSV into memory (`List<String[]>`).  
  - `transform()` → Applies the rules in order:  
    1. Convert `Name` to uppercase.  
    2. Apply 10% discount to Electronics.  
    3. Re-categorize Electronics over $500 → "Premium Electronics".  
    4. Compute `PriceRange` based on final price.  
  - `load()` → Writes transformed data back to CSV.  
- Uses `BigDecimal` with `RoundingMode.HALF_UP` to ensure proper financial rounding.  
- Gracefully handles:
  - Missing input file (prints error and exits).  
  - Empty input file (only header → output contains just the header).  

## How to Compile & Run
From the **project root** (contains `src/` and `data/`):

```bash
# Compile
javac -d bin src/org/howard/edu/lsp/assignment2/ETLPipeline.java

# Run
java -cp bin org.howard.edu.lsp.assignment2.ETLPipeline

The program creates/overwrites data/transformed_products.csv.

## Testing Strategy

I tested the program with the three required cases:

## Case A – Normal Input

Input:

ProductID,Name,Price,Category
1,Book,12.99,Education
2,Laptop,999.99,Electronics
3,Notebook,2.49,Stationery
4,Headphones,199.99,Electronics
5,Pencil,0.99,Stationery
6,Smartphone,699.49,Electronics


Output:

ProductID,Name,Price,Category,PriceRange
1,BOOK,12.99,Education,Medium
2,LAPTOP,899.99,Premium Electronics,Premium
3,NOTEBOOK,2.49,Stationery,Low
4,HEADPHONES,179.99,Electronics,High
5,PENCIL,0.99,Stationery,Low
6,SMARTPHONE,629.54,Premium Electronics,Premium

## Case B – Empty Input

Input:

ProductID,Name,Price,Category


Output:

ProductID,Name,Price,Category,PriceRange

## Case C – Missing Input

If data/products.csv is missing, the program prints:

Error: Input file 'data/products.csv' not found.


and exits without crashing.

## AI Usage

I used ChatGPT to help draft the ETL pipeline.
It assisted with:

Structuring the program into extract/transform/load methods.

Choosing rounding with BigDecimal and RoundingMode.HALF_UP.

Verifying the transformation order.


## My Use of AI:
I directly used the structure but added additional comments, adjusted variable names, and verified the transformation logic matched the assignment’s golden outputs.


## External Sources
https://www.baeldung.com/java-csv
Learned about splitting CSV lines.

Adapted to the assignment’s constraints (fields contain no quotes/commas → used simple split(",")).