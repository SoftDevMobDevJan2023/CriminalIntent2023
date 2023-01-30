package au.edu.swin.sdmd.criminalintent2023

import java.util.*

data class Crime(val id: UUID,
                 var title: String,
                 var date: Date,
                 var isSolved: Boolean)