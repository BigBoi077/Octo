package cegepst.example.octo.views.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import cegepst.example.octo.R
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.stored.User
import cegepst.example.octo.viewModels.BaseViewModel

class WishListDialog(private val card: Card, private val viewModel: BaseViewModel, context: Context, private val user: User, private val alert: (String) -> Unit) : Dialog(context) {

    private lateinit var input: EditText
    private lateinit var total: TextView
    private lateinit var title: TextView
    private lateinit var confirm: Button
    private lateinit var cancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wish_list_dialog)
        initialize()
        setContent()
        setActions()
    }

    private fun initialize() {
        input = findViewById(R.id.inputNbrCopies)
        total = findViewById(R.id.totalPrice)
        title = findViewById(R.id.wishListTitle)
        confirm = findViewById(R.id.actionConfirm)
        cancel = findViewById(R.id.actionCancel)
    }

    @SuppressLint("SetTextI18n")
    private fun setContent() {
        title.text = "How many copies of ${card.name} would you like to add to your wish list ?"
        total.text = "${card.prices?.get("usd")}$"
    }

    private fun setActions() {
        input.doAfterTextChanged {
            this.calculateTotal()
        }
        cancel.setOnClickListener {
            this.dismiss()
        }
        confirm.setOnClickListener {
            val quantity: Int = if (input.text.isNullOrBlank()) {
                1
            } else {
                this.input.text.toString().toInt()
            }
            calculateTotal()
            val price = this.total.text.toString().toDouble()
            this.viewModel.insertCard(user.id, card, price, quantity)
            this.alert("Added ${quantity} ${card.name} to your wishlist.")
            this.dismiss()
        }
    }

    private fun calculateTotal() {
        try {
            val quantity = input.text.toString().toInt()
            val price = card.prices?.get("usd").toString().toDouble()
            total.text = String.format("%.2f", quantity * price)
        } catch (ex: Exception) {
            total.text = card.prices?.get("usd").toString()
        }
    }


}
