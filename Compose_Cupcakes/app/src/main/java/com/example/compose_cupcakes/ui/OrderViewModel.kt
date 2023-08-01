package com.example.compose_cupcakes.ui

import androidx.lifecycle.ViewModel
import com.example.compose_cupcakes.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

/** Price for a single cupcake*/
private const val PRICE_PER_CUPCAKE = 3500.00

/** Additional const for some day pickup of an order*/
private const val PRICE_FOR_SAME_DAY_PICKUP = 2000.00

/**
 * [OrderViewModel] holds information about a cupcake order in terms of quantity, flavor, and
 * pickup date. It also knows how to calculate the total price based on these order details.
 */

class OrderViewModel : ViewModel() {
    /**
     * Cupcake state for this order
     */
    private val _uiState = MutableStateFlow(
        OrderUiState(
            pickupOptions = pickupOptions()
        )
    )
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()
    /**
     * Set the quantity [numberCupcakes] of cupcakes for this order's state and update the price
     */
    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    /**
     * Set the [desiredFlavor] of cupcakes for this order's state.
     * Only 1 flavor can be selected for the whole order.
     */
    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(
                flavor = desiredFlavor
            )
        }
    }

    /**
     * Set the [pickupDate] for this order's state and update the price
     */
    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    /**
     * Reset the order state
     */
    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    /**
     * Returns the calculated price based on the order details.
     */
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        return NumberFormat.getCurrencyInstance().format(calculatedPrice)
    }

    /**
     * Returns a list of date options starting with the current date and the following 3 dates
     */
    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()

        Locale.setDefault(Locale("es", "CO"))

        // 'at' HH:mm:ss
        val formatter = SimpleDateFormat("E, d MMMM", Locale.getDefault())

        formatter.timeZone = TimeZone.getTimeZone("America/Bogota")

        val calendar = Calendar.getInstance()
        // Add current date and the following 3 dates.
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}