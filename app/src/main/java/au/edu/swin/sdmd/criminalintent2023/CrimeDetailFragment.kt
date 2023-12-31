package au.edu.swin.sdmd.criminalintent2023

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import au.edu.swin.sdmd.criminalintent2023.databinding.FragmentCrimeDetailBinding
import kotlinx.coroutines.launch
import java.util.*

// ch13
//private const val TAG = "CrimeDetailFragment"

class CrimeDetailFragment: Fragment() {

  //View Binding will generate a binding class that you
  //can use to inflate and bind your layout
  private var _binding: FragmentCrimeDetailBinding? = null
  private val binding
    get() = checkNotNull(_binding) {
      "Cannot access binding because it is null. Is the view visible?"
    }

//  private lateinit var crime: Crime


  /* ch13
  The Safe Args plugin not only generates code to perform type-safe
navigation but also allows you to safely access navigation arguments once
the user is at their destination. By using the navArgs property delegate,
you can access the navigation arguments for a particular destination in a
type-safe manner
   */
  private val args: CrimeDetailFragmentArgs by navArgs()

  private val crimeDetailViewModel: CrimeDetailViewModel by viewModels {
    CrimeDetailViewModelFactory(args.crimeId)
  }

  /**
   * configure the fragment instance but do not inflate the fragment’s view here.
   * Fragment's view inflation is performed by onCreateView.
   */
/* ch13:
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    crime = Crime(
      id = UUID.randomUUID(),
      title = "",
      date = Date(),
      isSolved = false
    )

    Log.d(TAG, "The crime ID is: ${args.crimeId}")
  }*/

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
        // ch13:
        // crime = crime.copy(title = text.toString())
        crimeDetailViewModel.updateCrime { oldCrime ->
          oldCrime.copy(title = text.toString())
        }
      }

      // ch13
      viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
          crimeDetailViewModel.crime.collect { crime ->
            crime?.let { updateUi(it) }
          }
        }
      }

      // date button
      crimeDate.apply {
        // ch13
        // text = crime.date.toString()
        isEnabled = false
      }

      // isSolved checkbox
      crimeSolved.setOnCheckedChangeListener { _, isChecked ->
        // ch13:        crime = crime.copy(isSolved = isChecked)
        crimeDetailViewModel.updateCrime { oldCrime ->
          oldCrime.copy(isSolved = isChecked)
        }
      }
    }
  }

  /* ch13
   look similar to the code you had before.
   The one piece that is a little different is where you set the text on the
  EditText. There, you need to check whether the existing value and the
  new value being passed in are different. If they are different, then you
  update the EditText. If they are the same, you do nothing. This will
  prevent an infinite loop when you start listening to changes on the
  EditText.
  * */
  private fun updateUi(crime: Crime) {
    binding.apply {
      if (crimeTitle.text.toString() != crime.title) {
        crimeTitle.setText(crime.title)
      }
      crimeDate.text = crime.date.toString()
      crimeSolved.isChecked = crime.isSolved
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