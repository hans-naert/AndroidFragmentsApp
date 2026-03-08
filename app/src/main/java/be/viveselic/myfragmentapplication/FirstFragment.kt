package be.viveselic.myfragmentapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

private const val string = "Hello from First"

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnToSecond = view.findViewById<Button>(R.id.btn_to_second)
        
        // Check if we are in dual pane
        val isDualPane = resources.getBoolean(R.bool.is_dual_pane)
        if (isDualPane) {
            // Hide the button if SecondFragment is already visible next to us
            btnToSecond.visibility = View.GONE
        } else {
            // Show the button and set up navigation for portrait mode
            btnToSecond.visibility = View.VISIBLE
            btnToSecond.setOnClickListener {
                val secondFragment = SecondFragment.newInstance(getString(R.string.hello_from_first), "456")
                
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, secondFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
