package au.edu.swin.sdmd.criminalintent2023

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import au.edu.swin.sdmd.criminalintent2023.databinding.FragmentCrimeDetailBinding
import java.util.*

class CrimeDetailFragment: Fragment() {

  //View Binding will generate a binding class that you
  //can use to inflate and bind your layout
  private var _binding: FragmentCrimeDetailBinding? = null
  private val binding
    get() = checkNotNull(_binding) {
      "Cannot access binding because it is null. Is the view visible?"
    }

  private lateinit var crime: Crime

  /**
   * configure the fragment instance but do not inflate the fragment’s view here.
   * Fragment's view inflation is performed by onCreateView.
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    crime = Crime(
      id = UUID.randomUUID(),
      title = "",
      date = Date(),
      isSolved = false
    )
  }

  /**
   * inflate and bind the layout for the fragment’s view and return the inflated
   * View to the hosting activity
   **/
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding =
      FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)

    return binding.root
  }

  /**
   * invoked immediately after onCreateView(…), to wire up the views
   */
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.apply {
      // a listener that will be invoked whenever the text in the
      // crimeTitle is changed
      crimeTitle.doOnTextChanged { text, _, _, _ ->
        crime = crime.copy(title = text.toString())
      }

      // date button
      crimeDate.apply {
        text = crime.date.toString()
        isEnabled = false
      }

      // isSolved checkbox
      crimeSolved.setOnCheckedChangeListener { _, isChecked ->
        crime = crime.copy(isSolved = isChecked)
      }
    }
  }

  /**
   * Nulling out references to your view
   **/
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}