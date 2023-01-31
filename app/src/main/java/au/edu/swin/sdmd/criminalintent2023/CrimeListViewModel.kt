package au.edu.swin.sdmd.criminalintent2023

import androidx.lifecycle.ViewModel
import java.util.*

/**
 * @version chapter 10
 */
class CrimeListViewModel : ViewModel() {
  val crimes = mutableListOf<Crime>()
  init {
    for (i in 0 until 100) {
      val crime = Crime(
        id = UUID.randomUUID(),
        title ="Crime #$i",
        date = Date(),
        isSolved = i % 2 == 0
      )
      crimes += crime
    }
  }
}
