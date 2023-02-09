package au.edu.swin.sdmd.criminalintent2023

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @version
 * - ch09: created
 * - ch12: makes @Entity for Room db access
 */
@Entity
data class Crime(
  @PrimaryKey val id: UUID,
  var title: String,
  var date: Date,
  var isSolved: Boolean)