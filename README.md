# SainsburysNathanLeigh

Java Programming challenge which scrapes a test Sainsburys website and returns product details as JSON.

More Details can be found here.


https://jsainsburyplc.github.io/serverside-test/



## How to run

Run with the sainsburys test site URL
```
java -jar out/artifacts/SainsburysNathanLeigh_jar/SainsburysNathanLeigh.jar <URL>
```

Example
```
java -jar out/artifacts/SainsburysNathanLeigh_jar/SainsburysNathanLeigh.jar https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html
```

## Expected Output

```
{
   "results":[
      {
         "title":"Sainsbury's Strawberries 400g",
         "kcal_per_100g":33,
         "unit_price":1.75,
         "description":"by Sainsbury's strawberries"
      },
      {
         "title":"Sainsbury's Blueberries 200g",
         "unit_price":1.75,
         "description":"by Sainsbury's blueberries"
      },
      {
         "title":"Sainsbury's Raspberries 225g",
         "unit_price":1.75,
         "description":"by Sainsbury's raspberries"
      },
      {
         "title":"Sainsbury's Blackberries, Sweet 150g",
         "kcal_per_100g":32,
         "unit_price":1.5,
         "description":"by Sainsbury's blackberries"
      },
      {
         "title":"Sainsbury's Blueberries 400g",
         "kcal_per_100g":45,
         "unit_price":3.25,
         "description":"by Sainsbury's blueberries"
      },
      {
         "title":"Sainsbury's Blueberries, SO Organic 150g",
         "unit_price":2.0,
         "description":"So Organic blueberries"
      },
      {
         "title":"Sainsbury's Raspberries, Taste the Difference 150g",
         "unit_price":2.5,
         "description":"Ttd raspberries"
      },
      {
         "title":"Sainsbury's Cherries 400g",
         "unit_price":2.5,
         "description":"by Sainsbury's Family Cherry Punnet"
      },
      {
         "title":"Sainsbury's Blackberries, Tangy 150g",
         "unit_price":1.5,
         "description":"by Sainsbury's blackberries"
      },
      {
         "title":"Sainsbury's Strawberries, Taste the Difference 300g",
         "kcal_per_100g":27,
         "unit_price":2.5,
         "description":"Ttd strawberries"
      },
      {
         "title":"Sainsbury's Cherry Punnet 200g",
         "unit_price":1.5,
         "description":"Cherries"
      },
      {
         "title":"Sainsbury's Mixed Berries 300g",
         "unit_price":3.5,
         "description":"by Sainsbury's mixed berries"
      },
      {
         "title":"Sainsbury's Mixed Berry Twin Pack 200g",
         "unit_price":2.75,
         "description":"Mixed Berries"
      },
      {
         "title":"Sainsbury's Redcurrants 150g",
         "unit_price":2.5,
         "description":"by Sainsbury's redcurrants"
      },
      {
         "title":"Sainsbury's Cherry Punnet, Taste the Difference 200g",
         "unit_price":2.5,
         "description":"Cherry Punnet"
      },
      {
         "title":"Sainsbury's Blackcurrants 150g",
         "unit_price":1.75,
         "description":""
      },
      {
         "title":"Sainsbury's British Cherry & Strawberry Pack 600g",
         "unit_price":4.0,
         "description":"British Cherry & Strawberry Mixed Pack"
      }
   ],
   "total":{
      "gross":39.5,
      "vat":7.9
   }
}

```