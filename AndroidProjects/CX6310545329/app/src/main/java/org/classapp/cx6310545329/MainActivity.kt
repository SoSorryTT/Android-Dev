package org.classapp.cx6310545329

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import org.classapp.cx6310545329.ui.theme.CX6310545329Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val screenContext = LocalContext.current
            val currenciesList = remember { mutableStateListOf<Currency?>() }

            val onFirebaseQueryFailed = { e:Exception ->
                Toast.makeText(screenContext, e.message,
                    Toast.LENGTH_LONG).show()
            }

            val onFirebaseQuerySuccess = { result:QuerySnapshot ->
                if (!result.isEmpty) {
                    val resultDocuments = result.documents
                    for (document in resultDocuments) {
                        val currency:Currency? = document.toObject(Currency::class.java)
                        currenciesList.add(currency)

                        Toast.makeText(screenContext,
                            "${currency?.currency} : ${currency?.rate}",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
            getCurrenciesFromFirebase(onFirebaseQuerySuccess, onFirebaseQueryFailed)
            CX6310545329Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
//                    MainActivityScreen()
                    CurrencyList(currencies = currenciesList)
                }
            }
        }
    }
}

data class Currency(
    val currency:String? = "",
    val rate:String? = ""
)

private fun getCurrenciesFromFirebase(onSuccess: (QuerySnapshot) -> Unit,
                                     onFailure: (Exception) -> Unit)
{
    val db = Firebase.firestore
    db.collection("currencies").get()
        .addOnSuccessListener { result -> onSuccess(result) }
        .addOnFailureListener { exception -> onFailure(exception) }
}

//@Composable
//fun MainActivityScreen() {
//    val screenContext = LocalContext.current
//    val currenciesList = remember { mutableStateListOf<Currency?>() }
//
//    val onFirebaseQueryFailed = { e:Exception ->
//        Toast.makeText(screenContext, e.message,
//            Toast.LENGTH_LONG).show()
//    }
//
//    val onFirebaseQuerySuccess = { result:QuerySnapshot ->
//        if (!result.isEmpty) {
//            val resultDocuments = result.documents
//            for (document in resultDocuments) {
//                val currency:Currency? = document.toObject(Currency::class.java)
//                currenciesList.add(currency)
//
//                Toast.makeText(screenContext,
//                    "${currency?.currency_name} : ${currency?.currency_rate}",
//                    Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//    getCurrenciesFromFirebase(onFirebaseQuerySuccess, onFirebaseQueryFailed)
//}

@Composable
fun CurrencyItem(currency:Currency) {
    ElevatedCard(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
//            TODO CHANGE COLOR
            containerColor = colorResource(id = R.color.teal_200))) {
        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp)) {
            Text(text = currency.currency!!,
                style = TextStyle(color = colorResource(id = R.color.purple_200),
                    fontSize = 20.sp)
            )
            Text(text = currency.rate!!,
                style = TextStyle(color = colorResource(id = R.color.purple_200),
                    fontSize = 20.sp)
            )
        }
    }
}

@Composable
fun CurrencyList(currencies: List<Currency?>) {
    LazyColumn(contentPadding = PaddingValues(all = 4.dp)) {
        items(items = currencies.filterNotNull()) {
            CurrencyItem(currency = it)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CX6310545329Theme {
        Greeting("Android")
    }
}