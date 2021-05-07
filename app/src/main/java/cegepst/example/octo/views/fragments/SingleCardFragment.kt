package cegepst.example.octo.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.base.Legality
import cegepst.example.octo.views.adapters.LegalityAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SingleCardFragment : Fragment() {

    internal lateinit var view: View
    private lateinit var card: Card
    private lateinit var legalityAdapter: LegalityAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        return
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.view = view
        setContent()
    }

    private fun setContent() {
        setAdapter()
        setCardInformation()
        setCollectorInformation()
        setAddWishListEvent()
    }

    private fun setAddWishListEvent() {
        view.findViewById<FloatingActionButton>(R.id.actionAddWishlist).setOnClickListener {

        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    private fun setCollectorInformation() {
        view.findViewById<TextView>(R.id.releaseDate).text = "Released on ${card.released}"
        view.findViewById<TextView>(R.id.cardSet).text = card.set
        view.findViewById<TextView>(R.id.collectorNumber).text =
                "Collector number : ${card.collectorNumber}"
        view.findViewById<TextView>(R.id.edhrecRank).text =
                "EDHREC rank : ${card.edhrecRank.toString()}"
        view.findViewById<TextView>(R.id.cardSet).text = card.setName
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun setCardInformation() {
        Glide.with(view).load(card.imageUris?.get("art_crop")).centerCrop()
            .into(view.findViewById(R.id.imageArt))
        view.findViewById<TextView>(R.id.cardName).text = card.name
        view.findViewById<TextView>(R.id.cardTypeLine).text = card.typeLine
        view.findViewById<TextView>(R.id.manaCost).text = card.manaCost
        view.findViewById<TextView>(R.id.convertedManaCost).text = "Cmc : ${card.convertedManaCost}"
        view.findViewById<TextView>(R.id.oracleText).text = card.oracleText
    }

    private fun setAdapter() {
        this.legalityAdapter = createLegalityAdapter()
        this.recyclerView = view.findViewById(R.id.legalitiesList)
        this.recyclerView.adapter = this.legalityAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(requireView().context)
        this.legalityAdapter.notifyDataSetChanged()
    }

    private fun createLegalityAdapter(): LegalityAdapter {
        val list = ArrayList<Legality>()
        card.legalities?.forEach { (key, value) -> list.add(Legality(key, value)) }
        return LegalityAdapter(list as List<Legality>)
    }

    private fun addToWishList() {

    }

    companion object {
        @JvmStatic
        fun newInstance(card: Card) =
                SingleCardFragment().apply {
                    this.card = card
                }
    }
}