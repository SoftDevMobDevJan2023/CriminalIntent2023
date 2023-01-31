package au.edu.swin.sdmd.criminalintent2023

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import au.edu.swin.sdmd.criminalintent2023.databinding.FragmentCrimeListBinding

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

    //  instantiate an instance with your crime data and connect it to your RecyclerView
    val crimes = crimeListViewModel.crimes
    val adapter = CrimeListAdapter(crimes)
    binding.crimeRecyclerView.adapter = adapter

    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
