# DefaultApi

All URIs are relative to *http://change.local*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**apiV1DonationsCarbonCalculateGet**](DefaultApi.md#apiV1DonationsCarbonCalculateGet) | **GET** /api/v1/donations/carbon_calculate | Calculate shipping carbon offset |
| [**apiV1DonationsCarbonStatsGet**](DefaultApi.md#apiV1DonationsCarbonStatsGet) | **GET** /api/v1/donations/carbon_stats | Retrieve carbon offset stats |
| [**apiV1DonationsCreatePost**](DefaultApi.md#apiV1DonationsCreatePost) | **POST** /api/v1/donations/create | Create a donation |
| [**apiV1DonationsCryptoCalculateGet**](DefaultApi.md#apiV1DonationsCryptoCalculateGet) | **GET** /api/v1/donations/crypto_calculate | Calculate crypto carbon offset |
| [**apiV1DonationsIndexGet**](DefaultApi.md#apiV1DonationsIndexGet) | **GET** /api/v1/donations/index | List your donations |
| [**apiV1DonationsShowGet**](DefaultApi.md#apiV1DonationsShowGet) | **GET** /api/v1/donations/show | Retrieve a donation |
| [**apiV1NonprofitsListGet**](DefaultApi.md#apiV1NonprofitsListGet) | **GET** /api/v1/nonprofits/list | Search a nonprofit |
| [**apiV1NonprofitsShowGet**](DefaultApi.md#apiV1NonprofitsShowGet) | **GET** /api/v1/nonprofits/show | Show a nonprofit |


<a id="apiV1DonationsCarbonCalculateGet"></a>
# **apiV1DonationsCarbonCalculateGet**
> apiV1DonationsCarbonCalculateGet(weightLb, originAddress, destinationAddress, distanceMi, transportationMethod)

Calculate shipping carbon offset

Calculates the donation amount (to CarbonFund 501\\(c\\)3) needed to offset a physical shipment. This calculation depends on the weight, primary transportation method, and distance of the shipment. Provide the distance of the shipment using the origin and destination address, or directly with the number of miles. For convenience, this endpoint also returns the id of the nonprofit CarbonFund, for making a subsequent donation to. See the [Carbon offsets guide](/recipes/carbon-offsets/) for more on using this endpoint.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    BigDecimal weightLb = new BigDecimal("3.5"); // BigDecimal | The total weight (in pounds) of the shipment.
    BigDecimal originAddress = new BigDecimal("60148"); // BigDecimal | The origin zip code (US only) of the shipment. If you send this parameter, also send `destination_address`.
    BigDecimal destinationAddress = new BigDecimal("94133"); // BigDecimal | The destination zip code (US only) of the shipment. If you send this parameter, also send `origin_address`.
    BigDecimal distanceMi = new BigDecimal(78); // BigDecimal | The total distance (in miles) of the shipment. You can use this parameter in place of `origin_address` and `destination_address`.
    String transportationMethod = "air"; // String | The primary transportation method of the shipment.
    try {
      apiInstance.apiV1DonationsCarbonCalculateGet(weightLb, originAddress, destinationAddress, distanceMi, transportationMethod);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1DonationsCarbonCalculateGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **weightLb** | **BigDecimal**| The total weight (in pounds) of the shipment. | |
| **originAddress** | **BigDecimal**| The origin zip code (US only) of the shipment. If you send this parameter, also send &#x60;destination_address&#x60;. | [optional] |
| **destinationAddress** | **BigDecimal**| The destination zip code (US only) of the shipment. If you send this parameter, also send &#x60;origin_address&#x60;. | [optional] |
| **distanceMi** | **BigDecimal**| The total distance (in miles) of the shipment. You can use this parameter in place of &#x60;origin_address&#x60; and &#x60;destination_address&#x60;. | [optional] |
| **transportationMethod** | **String**| The primary transportation method of the shipment. | [optional] [enum: air, truck, rail, sea] |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful |  -  |

<a id="apiV1DonationsCarbonStatsGet"></a>
# **apiV1DonationsCarbonStatsGet**
> apiV1DonationsCarbonStatsGet(id)

Retrieve carbon offset stats

Measures your carbon offset impact in relatable terms. Provide the id of a donation to CarbonFund to see stats about that specific donation. If you omit the donation id, this endpoint returns aggregate stats for all of your CarbonFund donations.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    BigDecimal id = new BigDecimal("d_NuYL6M2C1kjecXpWzKVODw7W"); // BigDecimal | The id of a donation to the CarbonFund nonprofit. Ids are returned when a donation is created. If an ID is not provided, the total stats for all donations to CarbonFund are returned.
    try {
      apiInstance.apiV1DonationsCarbonStatsGet(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1DonationsCarbonStatsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **BigDecimal**| The id of a donation to the CarbonFund nonprofit. Ids are returned when a donation is created. If an ID is not provided, the total stats for all donations to CarbonFund are returned. | [optional] |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful |  -  |

<a id="apiV1DonationsCreatePost"></a>
# **apiV1DonationsCreatePost**
> apiV1DonationsCreatePost(amount, nonprofitId, fundingSource, zipCode)

Create a donation

Creates a donation to any nonprofit. CHANGE keeps track of your donations, bills you at the end of the month, and handles the nonprofit payouts for you.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String amount = "500"; // String | The amount of the donation in cents.
    String nonprofitId = "n_IfEoPCaPqVsFAUI5xl0CBUOx"; // String | The id of a nonprofit from the CHANGE network.
    String fundingSource = "merchant"; // String | Source of the donation funds. If you are collecting payment from your customer for the donation, use `customer`.
    String zipCode = "94104"; // String | The customer's zip code. Provide this to unlock geographic insights.
    try {
      apiInstance.apiV1DonationsCreatePost(amount, nonprofitId, fundingSource, zipCode);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1DonationsCreatePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **amount** | **String**| The amount of the donation in cents. | |
| **nonprofitId** | **String**| The id of a nonprofit from the CHANGE network. | |
| **fundingSource** | **String**| Source of the donation funds. If you are collecting payment from your customer for the donation, use &#x60;customer&#x60;. | [enum: merchant, customer] |
| **zipCode** | **String**| The customer&#39;s zip code. Provide this to unlock geographic insights. | [optional] |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Donation successful |  -  |
| **400** | Param &#x60;nonprofit_id&#x60; invalid |  -  |

<a id="apiV1DonationsCryptoCalculateGet"></a>
# **apiV1DonationsCryptoCalculateGet**
> apiV1DonationsCryptoCalculateGet(currency, count)

Calculate crypto carbon offset

Calculates the donation amount (to CarbonFund 501\\(c\\)3) needed to offset a cryptocurrency transaction. For convenience, this endpoint also returns the id of the nonprofit CarbonFund, for making a subsequent donation to. See the [Carbon offsets guide](/recipes/carbon-offsets/) for more on using this endpoint.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String currency = "eth"; // String | The currency of the transaction.
    BigDecimal count = new BigDecimal("2"); // BigDecimal | The number of transactions to offset.
    try {
      apiInstance.apiV1DonationsCryptoCalculateGet(currency, count);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1DonationsCryptoCalculateGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **currency** | **String**| The currency of the transaction. | [enum: eth, btc] |
| **count** | **BigDecimal**| The number of transactions to offset. | [optional] |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful |  -  |

<a id="apiV1DonationsIndexGet"></a>
# **apiV1DonationsIndexGet**
> apiV1DonationsIndexGet(page)

List your donations

Retrieves a list of donations you&#39;ve previously made. The donations are returned in order of creation, with the most recent donations appearing first. This endpoint is paginated.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    BigDecimal page = new BigDecimal("1"); // BigDecimal | Which page to return. This endpoint is paginated, and returns maximum 30 donations per page.
    try {
      apiInstance.apiV1DonationsIndexGet(page);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1DonationsIndexGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **page** | **BigDecimal**| Which page to return. This endpoint is paginated, and returns maximum 30 donations per page. | [optional] |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful |  -  |

<a id="apiV1DonationsShowGet"></a>
# **apiV1DonationsShowGet**
> apiV1DonationsShowGet(id)

Retrieve a donation

Retrieves the details of a donation you&#39;ve previously made.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "d_NuYL6M2C1kjecXpWzKVODw7W"; // String | The id of a donation. Ids are returned when a donation is created.
    try {
      apiInstance.apiV1DonationsShowGet(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1DonationsShowGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The id of a donation. Ids are returned when a donation is created. | |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful |  -  |

<a id="apiV1NonprofitsListGet"></a>
# **apiV1NonprofitsListGet**
> apiV1NonprofitsListGet(name, page)

Search a nonprofit

Retrieves a list of nonprofits whose names match the provided name. This endpoint is paginated.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String name = "Some Nonprofit"; // String | A string to search.
    BigDecimal page = new BigDecimal("1"); // BigDecimal | The page to return. This endpoint is paginated, and returns up to 30 nonprofits at a time.
    try {
      apiInstance.apiV1NonprofitsListGet(name, page);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1NonprofitsListGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **name** | **String**| A string to search. | [optional] |
| **page** | **BigDecimal**| The page to return. This endpoint is paginated, and returns up to 30 nonprofits at a time. | [optional] |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List successful |  -  |

<a id="apiV1NonprofitsShowGet"></a>
# **apiV1NonprofitsShowGet**
> apiV1NonprofitsShowGet(id)

Show a nonprofit

Retrieves information for a nonprofit.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://change.local");
    
    // Configure HTTP basic authorization: basic_auth
    HttpBasicAuth basic_auth = (HttpBasicAuth) defaultClient.getAuthentication("basic_auth");
    basic_auth.setUsername("YOUR USERNAME");
    basic_auth.setPassword("YOUR PASSWORD");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "n_IfEoPCaPqVsFAUI5xl0CBUOx"; // String | The id of a nonprofit from the CHANGE network.
    try {
      apiInstance.apiV1NonprofitsShowGet(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#apiV1NonprofitsShowGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The id of a nonprofit from the CHANGE network. | |

### Return type

null (empty response body)

### Authorization

[basic_auth](../README.md#basic_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Show successful |  -  |

