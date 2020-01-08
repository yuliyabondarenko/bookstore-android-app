package com.jubee.bookstore.ui.fragment.books.transition

import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_book_details.view.*


fun FragmentTransaction.setUpTransition(
    fragment: Fragment,
    id: Long,
    itemView: View
): FragmentTransaction {
    fragment.sharedElementEnterTransition = DetailsTransition()

    val nameView = itemView.bookName.apply { transitionName = "bookName$id" }
    val imageView = itemView.bookImage.apply { transitionName = "bookImage$id" }
    val priceView = itemView.bookPrice.apply { transitionName = "bookPrice$id" }
    val absentView = itemView.bookAbsent.apply { transitionName = "bookAbsent$id" }
    val addButton = itemView.addButton.apply { transitionName = "addButton$id" }

    addSharedElement(nameView, "bookName")
    addSharedElement(imageView, "bookImage")
    addSharedElement(priceView, "bookPrice")
    addSharedElement(absentView, "bookAbsent")
    addSharedElement(addButton, "addButton")

    return this
}

class DetailsTransition : TransitionSet() {
    init {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
        addTransition(ChangeTransform())
        addTransition(ChangeImageTransform())
    }
}