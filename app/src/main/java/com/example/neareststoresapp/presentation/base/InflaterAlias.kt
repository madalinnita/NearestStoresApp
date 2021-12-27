package com.example.neareststoresapp.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup

typealias FragmentInflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

typealias ActivityInflate<T> = (LayoutInflater) -> T
