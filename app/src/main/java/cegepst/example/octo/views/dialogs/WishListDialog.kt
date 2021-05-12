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
import cegepst.example.octo.viewModels.BaseViewModel
import kotlin.math.roundToInt

class WishListDialog(private val card: Card, private val viewModel: BaseViewModel, context: Context) : Dialog(context) {

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
            val price = this.total.text.toString().toDouble()
            val quantity = this.input.text.toString().toInt()
            this.viewModel.insertCard(0, card, price, quantity)
            this.dismiss()
        }
    }

    private fun calculateTotal() {
        try {
            val quantity = input.text.toString().toInt()
            val price = card.prices?.get("usd").toString().toDouble()
            total.text = (quantity * price).roundToInt().toString()
        } catch (ex: Exception) {
            total.text = card.prices?.get("usd").toString()
        }
    }
}
