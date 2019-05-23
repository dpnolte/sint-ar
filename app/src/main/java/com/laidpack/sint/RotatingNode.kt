package com.laidpack.sint

import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.QuaternionEvaluator
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem

class RotatingNode() : Node() {
    // We'll use Property Animation to make this node rotate.
    private var rotatingAnimation: ObjectAnimator? = null
    private var degreesPerSecond = 90.0f

    private var enableAnimation = false
    private var speedMultiplier = 1.0f

    override fun onUpdate(frameTime: FrameTime?) {
        super.onUpdate(frameTime)

        // Animation hasn't been set up.
        if (!enableAnimation || rotatingAnimation == null) {
            return
        }

        rotatingAnimation?.let { anim ->
            anim.resume()

            val animatedFraction = rotatingAnimation!!.animatedFraction
            rotatingAnimation!!.duration = getAnimationDuration()
            rotatingAnimation!!.setCurrentFraction(animatedFraction)
        }
    }

    fun startAnimation() {
        enableAnimation = true
        if (rotatingAnimation != null) {
            return
        }

        val anim = createAnimator()
        anim.target = this
        anim.duration = getAnimationDuration()
        anim.start()
        rotatingAnimation = anim
    }


    fun stopAnimation() {
        enableAnimation = false
        if (rotatingAnimation == null) {
            return
        }
        rotatingAnimation?.cancel()
        rotatingAnimation = null
    }

    /** Returns an ObjectAnimator that makes this node rotate.  */
    private fun createAnimator(): ObjectAnimator {
        // Node's setLocalRotation method accepts Quaternions as parameters.
        // First, set up orientations that will animate a circle.
        val orientation1 = Quaternion.axisAngle(Vector3(0.0f, 1.0f, 0.0f), 0f)
        val orientation2 = Quaternion.axisAngle(Vector3(0.0f, 1.0f, 0.0f), 120f)
        val orientation3 = Quaternion.axisAngle(Vector3(0.0f, 1.0f, 0.0f), 240f)
        val orientation4 = Quaternion.axisAngle(Vector3(0.0f, 1.0f, 0.0f), 360f)

        val orbitAnimation = ObjectAnimator()
        orbitAnimation.setObjectValues(orientation1, orientation2, orientation3, orientation4)

        // Next, give it the localRotation property.
        orbitAnimation.propertyName = "localRotation"

        // Use Sceneform's QuaternionEvaluator.
        orbitAnimation.setEvaluator(QuaternionEvaluator())

        //  Allow orbitAnimation to repeat forever
        orbitAnimation.repeatCount = ObjectAnimator.INFINITE
        orbitAnimation.repeatMode = ObjectAnimator.RESTART
        orbitAnimation.interpolator = LinearInterpolator()
        orbitAnimation.setAutoCancel(true)

        return orbitAnimation
    }

    private fun getAnimationDuration(): Long {
        return (1000 * 360 / (degreesPerSecond * speedMultiplier)).toLong()
    }

}