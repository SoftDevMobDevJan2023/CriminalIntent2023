package au.edu.swin.sdmd.criminalintent2023

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
//import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.swin.sdmd.criminalintent2023.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.launch

private const val TAG = "CrimeListFragment"

/**
 * @version: Chapter 10
 */
class CrimeListFragment : Fragment() {
  private var _binding: FragmentCrimeListBinding? = null
  private val binding
    get() = checkNotNull(_binding) {
      "Cannot access binding because it is null. Is the view visible?"
    }

  private val crimeListViewModel: CrimeListViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentCrimeListBinding.inflate(inflater, container, false)

    /*
     * Sets up layout manager for the crime list (recycler view).
     * Delegates the responsibility for positioning items on
     * the screen to the LayoutManager. The LayoutManager positions
     * each item and also defines how scrolling works.
     * LinearLayoutManager positions the items in
     * the list vertically, one after the other, like a LinearLayout
     */
    binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    /*
    With the repeatOnLifecycle(…) function, you can execute coroutine
    code while your fragment is in a specified lifecycle state. For example, you
    only want this coroutine code to execute while your fragment is in the
    started or resumed state. Also, repeatOnLifecycle is itself a
    suspending function. You will launch it in your view lifecycle scope, which
    will cause your work to be canceled permanently when your view is
    destroyed.
     */
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        val crimes = crimeListViewModel.loadCrimes()
        binding.crimeRecyclerView.adapter =
          CrimeListAdapter(crimes)
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
