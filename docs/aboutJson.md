``` java
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import java.nio.file.Files;
import java.nio.file.Paths;

// Load inflowJson
String inflowJson = new String(Files.readAllBytes(Paths.get("src/test/resources/inflow.json")));
JsonObject inflowBody = JsonParser.parseString(inflowJson).getAsJsonObject();
JsonArray inflowFunds = inflowBody.getAsJsonArray("funds");

// Get outflowJson from WebTestClient
String outflowJson = webTestClient.get().uri("/your-endpoint")
    .exchange()
    .expectStatus().isOk()
    .expectBody(String.class)
    .returnResult().getResponseBody();
JsonObject outflowPage = JsonParser.parseString(outflowJson).getAsJsonObject();
JsonArray outflowContent = outflowPage.getAsJsonArray("content");

// Verify same number of funds
assertEquals(inflowFunds.size(), outflowContent.size());

// Compare arrays
JSONAssert.assertEquals(inflowFunds.toString(), outflowContent.toString(), JSONCompareMode.LENIENT);
```
For jsonAssert, refer here: https://www.baeldung.com/jsonassert
For webTestClient, refer here: https://docs.spring.io/spring-framework/reference/testing/webtestclient.html
