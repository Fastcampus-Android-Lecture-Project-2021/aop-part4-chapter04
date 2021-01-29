package aop.fastcampus.part4.chapter04

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import aop.fastcampus.part4.chapter04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var isGateringMotionAnimating: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            if (binding.scrollView.scrollY > 150f.dpToPx(this@MainActivity).toInt()) {
                if (isGateringMotionAnimating.not()) {
                    binding.gatheringDigitalThingsMotionLayout.transitionToEnd()
                    binding.buttonShownMotionLayout.transitionToEnd()
                }
            } else {
                if (isGateringMotionAnimating.not()) {
                    binding.gatheringDigitalThingsMotionLayout.transitionToStart()
                    binding.buttonShownMotionLayout.transitionToStart()
                }
            }
        }

        binding.gatheringDigitalThingsMotionLayout.setTransitionListener(object : TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                isGateringMotionAnimating = true
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) = Unit

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                isGateringMotionAnimating = false
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) = Unit

        })
    }
}

fun Float.dpToPx(context: Context): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)
