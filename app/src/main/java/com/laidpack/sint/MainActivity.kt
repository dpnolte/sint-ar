package com.laidpack.sint

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.math.Quaternion



class MainActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var hidePlaneDiscoveryController: Boolean = false
    private var hasSelectedReichstag: Boolean = false
    private lateinit var assetsDirString: String
    private var foundItCard : Node? = null
    private var snackbar: Snackbar?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return
        }

        setContentView(R.layout.activity_main)
        assetsDirString = intent.getStringExtra("assetsDir")


        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment
        hidePlaneDiscoveryController = false



        createReichsTagRenderable()

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            if (reichstag == null) {
                // try to load again
                createReichsTagRenderable()
                return@setOnTapArPlaneListener
            }
            if (hasSelectedReichstag) {
                return@setOnTapArPlaneListener
            }
            if (buildingRenderable1 == null) {
                return@setOnTapArPlaneListener
            }
            // Create the Anchor.
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)

            createNodes(anchorNode)

            val arSceneView = arFragment.arSceneView
            hidePlaneDiscoveryController = true
            arFragment.planeDiscoveryController.hide()
            arSceneView.planeRenderer.isVisible = false

        }
    }

    override fun onResume() {
        super.onResume()
        if (hidePlaneDiscoveryController) {
            arFragment.planeDiscoveryController.hide()
            arFragment.arSceneView.planeRenderer.isVisible = false
        }
    }

    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     *
     * Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     *
     *
     * Finishes the activity if Sceneform can not run
     */
    @SuppressLint("ObsoleteSdkInt")
    fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later")
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show()
            activity.finish()
            return false
        }
        val openGlVersionString = (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion
        if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later")
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show()
            activity.finish()
            return false
        }
        return true
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val MIN_OPENGL_VERSION = 3.0
    }

    private fun showSnackbar(message: String) {
        if (snackbar == null) {
            val s = Snackbar.make(
                    this.findViewById(android.R.id.content),
                    message,
                    Snackbar.LENGTH_INDEFINITE
            )
            s.view.setBackgroundColor(-0x409B111E)
            s.show()
            snackbar = s
        } else {
            val s = snackbar as Snackbar
            s.setText(message)
            s.show()
        }
    }

    private lateinit var buildingRenderable1: ModelRenderable
    private lateinit var buildingRenderable2: ModelRenderable
    private lateinit var buildingRenderable3: ModelRenderable
    private lateinit var buildingRenderable4: ModelRenderable
    private lateinit var buildingRenderable5: ModelRenderable
    private lateinit var buildingRenderable6: ModelRenderable
    private lateinit var buildingRenderable7: ModelRenderable
    private lateinit var buildingRenderable8: ModelRenderable
    private lateinit var buildingRenderable9: ModelRenderable
    private lateinit var buildingRenderable10: ModelRenderable
    private lateinit var buildingRenderable11: ModelRenderable
    private lateinit var buildingRenderable12: ModelRenderable
    private lateinit var buildingRenderable13: ModelRenderable
    private lateinit var buildingRenderable14: ModelRenderable
    private lateinit var buildingRenderable15: ModelRenderable
    private lateinit var buildingRenderable16: ModelRenderable
    private lateinit var buildingRenderable17: ModelRenderable
    private lateinit var buildingRenderable18: ModelRenderable
    private lateinit var buildingRenderable19: ModelRenderable
    private lateinit var buildingRenderable20: ModelRenderable
    private lateinit var buildingRenderable21: ModelRenderable
    private lateinit var buildingRenderable22: ModelRenderable
    private lateinit var buildingRenderable23: ModelRenderable
    private lateinit var buildingRenderable24: ModelRenderable
    private lateinit var buildingRenderable25: ModelRenderable
    private lateinit var buildingRenderable26: ModelRenderable
    private lateinit var buildingRenderable27: ModelRenderable
    private lateinit var buildingRenderable28: ModelRenderable
    private lateinit var buildingRenderable29: ModelRenderable
    private lateinit var buildingRenderable30: ModelRenderable
    private lateinit var buildingRenderable31: ModelRenderable
    private lateinit var buildingRenderable32: ModelRenderable
    private lateinit var buildingRenderable33: ModelRenderable
    private lateinit var buildingRenderable34: ModelRenderable
    private lateinit var buildingRenderable35: ModelRenderable
    private lateinit var buildingRenderable36: ModelRenderable
    private lateinit var buildingRenderable37: ModelRenderable
    private lateinit var buildingRenderable38: ModelRenderable
    private lateinit var buildingRenderable39: ModelRenderable
    private lateinit var buildingRenderable40: ModelRenderable
    private lateinit var buildingRenderable41: ModelRenderable
    private lateinit var buildingRenderable42: ModelRenderable
    private lateinit var buildingRenderable43: ModelRenderable
    private var reichstag: ModelRenderable? = null
    private lateinit var buildingRenderable45: ModelRenderable
    private lateinit var buildingRenderable46: ModelRenderable
    private lateinit var buildingRenderable47: ModelRenderable
    private lateinit var buildingRenderable48: ModelRenderable
    private lateinit var buildingRenderable49: ModelRenderable
    private lateinit var buildingRenderable50: ModelRenderable
    private lateinit var buildingRenderable51: ModelRenderable
    private lateinit var buildingRenderable52: ModelRenderable
    private lateinit var buildingRenderable53: ModelRenderable
    private lateinit var buildingRenderable54: ModelRenderable
    private lateinit var buildingRenderable55: ModelRenderable
    private lateinit var buildingRenderable56: ModelRenderable
    private lateinit var buildingRenderable57: ModelRenderable
    private lateinit var buildingRenderable58: ModelRenderable
    private lateinit var buildingRenderable59: ModelRenderable
    private lateinit var buildingRenderable60: ModelRenderable
    private lateinit var buildingRenderable61: ModelRenderable
    private lateinit var buildingRenderable62: ModelRenderable
    private lateinit var buildingRenderable63: ModelRenderable
    private lateinit var buildingRenderable64: ModelRenderable
    private lateinit var buildingRenderable65: ModelRenderable
    private lateinit var buildingRenderable66: ModelRenderable
    private lateinit var buildingRenderable67: ModelRenderable
    private lateinit var buildingRenderable68: ModelRenderable

    private fun createNodes(anchorNode: AnchorNode) {
        val node1 = TransformableNode(arFragment.transformationSystem)
        node1.scaleController.minScale = 0.01f
        node1.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node1.setParent(anchorNode)
        node1.renderable = buildingRenderable1
        //node.select()
        node1.setParent(anchorNode)

        // reichstag
        val reichstagNode = RotatingNode()
        //reichstagNode.scaleController.minScale = 0.01f
        reichstagNode.localScale = Vector3(0.2f, 0.2f, 0.2f)
        reichstagNode.localPosition = Vector3(node1.localPosition.x + -2.022381000000132f, node1.localPosition.y, node1.localPosition.z + 0.8910125999973388f)
        reichstagNode.setParent(anchorNode)
        reichstagNode.renderable = reichstag
        //node.select()
        reichstagNode.setParent(anchorNode)
        reichstagNode.setOnTapListener { hitTestResult, motionEvent ->
            //reichstagNode.select()
            for (child in anchorNode.children.toList()) {
                if (child != reichstagNode) {
                    child.setParent(null)
                }
            }
            //reichstagNode.setParent(arFragment.arSceneView.scene.camera)
            //reichstagNode.localPosition = Vector3(0f, 0f, -1f)


            if (!hasSelectedReichstag) {
                reichstagNode.localScale = Vector3(0.4f, 0.4f, 0.4f)
                reichstagNode.startAnimation()
                hasSelectedReichstag = true
            }
            if (foundItCard == null) {
                val card = Node()
                card.setParent(reichstagNode.parent)
                //card.isEnabled = false
                //card.localPosition = Vector3(2.0f, 2.0f, 2.0f)
                val worldPosition = reichstagNode.worldPosition
                card.worldPosition = Vector3.add(reichstagNode.worldPosition, Vector3(0.0f, 0.7f, 0.0f))
                /*reichstagNode.localPosition.x,
                reichstagNode.localPosition.y,
                reichstagNode.localPosition.z)*/
                // rotate to camera
                val cameraPosition = arFragment.arSceneView.scene.camera.worldPosition
                val cardPosition = card.worldPosition
                val direction = Vector3.subtract(cameraPosition, cardPosition)
                val lookRotation = Quaternion.lookRotation(direction, Vector3.up())
                card.worldRotation = lookRotation

                ViewRenderable.builder()
                        .setView(this, R.layout.view_found_it)
                        .build()
                        .thenAccept { renderable ->
                            card.renderable = renderable
                            foundItCard = card
                        }
                        .exceptionally {
                            throwable -> throw AssertionError("Could not load card view.", throwable)
                        }
            }
            //val message = getString(R.string.gevonden)
            //showSnackbar(message)

        }
        val node2 = TransformableNode(arFragment.transformationSystem)
        node2.scaleController.minScale = 0.01f
        node2.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node2.localPosition = Vector3(node1.localPosition.x + -2.2602190000001428f, node1.localPosition.y, node1.localPosition.z + 1.4533723999978976f)
        node2.setParent(anchorNode)
        node2.renderable = buildingRenderable2
        //node.select()
        node2.setParent(anchorNode)
/*
        val node3 = TransformableNode(arFragment.transformationSystem)
        node3.scaleController.minScale = 0.01f
        node3.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node3.localPosition = Vector3(node1.localPosition.x + -2.112914600000113f, node1.localPosition.y, node1.localPosition.z + 0.2159531999990577f)
        node3.setParent(anchorNode)
        node3.renderable = buildingRenderable3
        //node.select()
        node3.setParent(anchorNode)

        val node4 = TransformableNode(arFragment.transformationSystem)
        node4.scaleController.minScale = 0.01f
        node4.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node4.localPosition = Vector3(node1.localPosition.x + -1.9399650000001203f, node1.localPosition.y, node1.localPosition.z + 0.007801599998492748f)
        node4.setParent(anchorNode)
        node4.renderable = buildingRenderable4
        //node.select()
        node4.setParent(anchorNode)

        val node5 = TransformableNode(arFragment.transformationSystem)
        node5.scaleController.minScale = 0.01f
        node5.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node5.localPosition = Vector3(node1.localPosition.x + -1.4363578000000417f, node1.localPosition.y, node1.localPosition.z + 0.7359591999993427f)
        node5.setParent(anchorNode)
        node5.renderable = buildingRenderable5
        //node.select()
        node5.setParent(anchorNode)

        val node6 = TransformableNode(arFragment.transformationSystem)
        node6.scaleController.minScale = 0.01f
        node6.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node6.localPosition = Vector3(node1.localPosition.x + -1.658408000000054f, node1.localPosition.y, node1.localPosition.z + 0.5523149999993621f)
        node6.setParent(anchorNode)
        node6.renderable = buildingRenderable6
        //node.select()
        node6.setParent(anchorNode)

        val node7 = TransformableNode(arFragment.transformationSystem)
        node7.scaleController.minScale = 0.01f
        node7.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node7.localPosition = Vector3(node1.localPosition.x + -2.067939800000022f, node1.localPosition.y, node1.localPosition.z + 0.2520665999996709f)
        node7.setParent(anchorNode)
        node7.renderable = buildingRenderable7
        //node.select()
        node7.setParent(anchorNode)

        val node8 = TransformableNode(arFragment.transformationSystem)
        node8.scaleController.minScale = 0.01f
        node8.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node8.localPosition = Vector3(node1.localPosition.x + -2.0772845999999845f, node1.localPosition.y, node1.localPosition.z + 1.4604341999976898f)
        node8.setParent(anchorNode)
        node8.renderable = buildingRenderable8
        //node.select()
        node8.setParent(anchorNode)

        val node9 = TransformableNode(arFragment.transformationSystem)
        node9.scaleController.minScale = 0.01f
        node9.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node9.localPosition = Vector3(node1.localPosition.x + -0.23270860000011453f, node1.localPosition.y, node1.localPosition.z + 0.7872125999972923f)
        node9.setParent(anchorNode)
        node9.renderable = buildingRenderable9
        //node.select()
        node9.setParent(anchorNode)

        val node10 = TransformableNode(arFragment.transformationSystem)
        node10.scaleController.minScale = 0.01f
        node10.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node10.localPosition = Vector3(node1.localPosition.x + -2.157100200000059f, node1.localPosition.y, node1.localPosition.z + 0.2487355999997817f)
        node10.setParent(anchorNode)
        node10.renderable = buildingRenderable10
        //node.select()
        node10.setParent(anchorNode)

        val node11 = TransformableNode(arFragment.transformationSystem)
        node11.scaleController.minScale = 0.01f
        node11.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node11.localPosition = Vector3(node1.localPosition.x + -0.18159900000009657f, node1.localPosition.y, node1.localPosition.z + 0.8772895999980392f)
        node11.setParent(anchorNode)
        node11.renderable = buildingRenderable11
        //node.select()
        node11.setParent(anchorNode)

        val node12 = TransformableNode(arFragment.transformationSystem)
        node12.scaleController.minScale = 0.01f
        node12.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node12.localPosition = Vector3(node1.localPosition.x + -0.18667420000001586f, node1.localPosition.y, node1.localPosition.z + 0.3788547999982257f)
        node12.setParent(anchorNode)
        node12.renderable = buildingRenderable12
        //node.select()
        node12.setParent(anchorNode)

        val node13 = TransformableNode(arFragment.transformationSystem)
        node13.scaleController.minScale = 0.01f
        node13.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node13.localPosition = Vector3(node1.localPosition.x + -1.0999340000000302f, node1.localPosition.y, node1.localPosition.z + 1.274931199999992f)
        node13.setParent(anchorNode)
        node13.renderable = buildingRenderable13
        //node.select()
        node13.setParent(anchorNode)

        val node14 = TransformableNode(arFragment.transformationSystem)
        node14.scaleController.minScale = 0.01f
        node14.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node14.localPosition = Vector3(node1.localPosition.x + -1.0341427999999724f, node1.localPosition.y, node1.localPosition.z + 1.3774139999994077f)
        node14.setParent(anchorNode)
        node14.renderable = buildingRenderable14
        //node.select()
        node14.setParent(anchorNode)

        val node15 = TransformableNode(arFragment.transformationSystem)
        node15.scaleController.minScale = 0.01f
        node15.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node15.localPosition = Vector3(node1.localPosition.x + -0.9898986000000151f, node1.localPosition.y, node1.localPosition.z + 1.3159163999982413f)
        node15.setParent(anchorNode)
        node15.renderable = buildingRenderable15
        //node.select()
        node15.setParent(anchorNode)


        val node16 = TransformableNode(arFragment.transformationSystem)
        node16.scaleController.minScale = 0.01f
        node16.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node16.localPosition = Vector3(node1.localPosition.x + -0.8475844000000508f, node1.localPosition.y, node1.localPosition.z + 1.3035633999999847f)
        node16.setParent(anchorNode)
        node16.renderable = buildingRenderable16
        //node.select()
        node16.setParent(anchorNode)

        val node17 = TransformableNode(arFragment.transformationSystem)
        node17.scaleController.minScale = 0.01f
        node17.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node17.localPosition = Vector3(node1.localPosition.x + -0.755649799999992f, node1.localPosition.y, node1.localPosition.z + 1.287263999998686f)
        node17.setParent(anchorNode)
        node17.renderable = buildingRenderable17
        //node.select()
        node17.setParent(anchorNode)

        val node18 = TransformableNode(arFragment.transformationSystem)
        node18.scaleController.minScale = 0.01f
        node18.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node18.localPosition = Vector3(node1.localPosition.x + -0.7186846000000515f, node1.localPosition.y, node1.localPosition.z + 1.2678905999986456f)
        node18.setParent(anchorNode)
        node18.renderable = buildingRenderable18
        //node.select()
        node18.setParent(anchorNode)

        val node19 = TransformableNode(arFragment.transformationSystem)
        node19.scaleController.minScale = 0.01f
        node19.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node19.localPosition = Vector3(node1.localPosition.x + -2.1333150000000387f, node1.localPosition.y, node1.localPosition.z + 1.423251399997389f)
        node19.setParent(anchorNode)
        node19.renderable = buildingRenderable19
        //node.select()
        node19.setParent(anchorNode)

        val node20 = TransformableNode(arFragment.transformationSystem)
        node20.scaleController.minScale = 0.01f
        node20.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node20.localPosition = Vector3(node1.localPosition.x + 0.0f, node1.localPosition.y, node1.localPosition.z + 0.0f)
        node20.setParent(anchorNode)
        node20.renderable = buildingRenderable20
        //node.select()
        node20.setParent(anchorNode)
*/
        val node21 = TransformableNode(arFragment.transformationSystem)
        node21.scaleController.minScale = 0.01f
        node21.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node21.localPosition = Vector3(node1.localPosition.x + 0.14712700000000042f, node1.localPosition.y, node1.localPosition.z + 0.27985479999915697f)
        node21.setParent(anchorNode)
        node21.renderable = buildingRenderable21
        //node.select()
        node21.setParent(anchorNode)

        val node22 = TransformableNode(arFragment.transformationSystem)
        node22.scaleController.minScale = 0.01f
        node22.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node22.localPosition = Vector3(node1.localPosition.x + -0.3567586000001029f, node1.localPosition.y, node1.localPosition.z + 0.6382505999994464f)
        node22.setParent(anchorNode)
        node22.renderable = buildingRenderable22
        //node.select()
        node22.setParent(anchorNode)

        val node23 = TransformableNode(arFragment.transformationSystem)
        node23.scaleController.minScale = 0.01f
        node23.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node23.localPosition = Vector3(node1.localPosition.x + -0.4145476000001509f, node1.localPosition.y, node1.localPosition.z + 0.7506971999973757f)
        node23.setParent(anchorNode)
        node23.renderable = buildingRenderable23
        //node.select()
        node23.setParent(anchorNode)

        val node24 = TransformableNode(arFragment.transformationSystem)
        node24.scaleController.minScale = 0.01f
        node24.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node24.localPosition = Vector3(node1.localPosition.x + -0.19647560000012165f, node1.localPosition.y, node1.localPosition.z + 0.6350289999973029f)
        node24.setParent(anchorNode)
        node24.renderable = buildingRenderable24
        //node.select()
        node24.setParent(anchorNode)

        val node25 = TransformableNode(arFragment.transformationSystem)
        node25.scaleController.minScale = 0.01f
        node25.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node25.localPosition = Vector3(node1.localPosition.x + -0.09735120000004827f, node1.localPosition.y, node1.localPosition.z + 0.7029773999995087f)
        node25.setParent(anchorNode)
        node25.renderable = buildingRenderable25
        //node.select()
        node25.setParent(anchorNode)
/*
        val node26 = TransformableNode(arFragment.transformationSystem)
        node26.scaleController.minScale = 0.01f
        node26.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node26.localPosition = Vector3(node1.localPosition.x + -2.395981800000118f, node1.localPosition.y, node1.localPosition.z + 0.6522039999981644f)
        node26.setParent(anchorNode)
        node26.renderable = buildingRenderable26
        //node.select()
        node26.setParent(anchorNode)

        val node27 = TransformableNode(arFragment.transformationSystem)
        node27.scaleController.minScale = 0.01f
        node27.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node27.localPosition = Vector3(node1.localPosition.x + -2.4167672000001406f, node1.localPosition.y, node1.localPosition.z + 0.7262097999977414f)
        node27.setParent(anchorNode)
        node27.renderable = buildingRenderable27
        //node.select()
        node27.setParent(anchorNode)

        val node28 = TransformableNode(arFragment.transformationSystem)
        node28.scaleController.minScale = 0.01f
        node28.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node28.localPosition = Vector3(node1.localPosition.x + -2.3127786000000925f, node1.localPosition.y, node1.localPosition.z + 0.5814631999994163f)
        node28.setParent(anchorNode)
        node28.renderable = buildingRenderable28
        //node.select()
        node28.setParent(anchorNode)

        val node29 = TransformableNode(arFragment.transformationSystem)
        node29.scaleController.minScale = 0.01f
        node29.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node29.localPosition = Vector3(node1.localPosition.x + -1.9795884000001025f, node1.localPosition.y, node1.localPosition.z + 0.10521179999923334f)
        node29.setParent(anchorNode)
        node29.renderable = buildingRenderable29
        //node.select()
        node29.setParent(anchorNode)

        val node30 = TransformableNode(arFragment.transformationSystem)
        node30.scaleController.minScale = 0.01f
        node30.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node30.localPosition = Vector3(node1.localPosition.x + -2.4165453999999955f, node1.localPosition.y, node1.localPosition.z + 0.2023105999978725f)
        node30.setParent(anchorNode)
        node30.renderable = buildingRenderable30
        //node.select()
        node30.setParent(anchorNode)

        val node31 = TransformableNode(arFragment.transformationSystem)
        node31.scaleController.minScale = 0.01f
        node31.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node31.localPosition = Vector3(node1.localPosition.x + -1.982625200000075f, node1.localPosition.y, node1.localPosition.z + 0.21917439999815544f)
        node31.setParent(anchorNode)
        node31.renderable = buildingRenderable31
        //node.select()
        node31.setParent(anchorNode)

        val node32 = TransformableNode(arFragment.transformationSystem)
        node32.scaleController.minScale = 0.01f
        node32.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node32.localPosition = Vector3(node1.localPosition.x + -2.0806330000001254f, node1.localPosition.y, node1.localPosition.z + 0.19860039999766743f)
        node32.setParent(anchorNode)
        node32.renderable = buildingRenderable32
        //node.select()
        node32.setParent(anchorNode)

        val node33 = TransformableNode(arFragment.transformationSystem)
        node33.scaleController.minScale = 0.01f
        node33.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node33.localPosition = Vector3(node1.localPosition.x + -2.056007000000136f, node1.localPosition.y, node1.localPosition.z + 0.2208907999971416f)
        node33.setParent(anchorNode)
        node33.renderable = buildingRenderable33
        //node.select()
        node33.setParent(anchorNode)

        val node34 = TransformableNode(arFragment.transformationSystem)
        node34.scaleController.minScale = 0.01f
        node34.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node34.localPosition = Vector3(node1.localPosition.x + -2.0987124000001134f, node1.localPosition.y, node1.localPosition.z + 0.29236359999922573f)
        node34.setParent(anchorNode)
        node34.renderable = buildingRenderable34
        //node.select()
        node34.setParent(anchorNode)
*/
        val node35 = TransformableNode(arFragment.transformationSystem)
        node35.scaleController.minScale = 0.01f
        node35.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node35.localPosition = Vector3(node1.localPosition.x + -0.3877564000000348f, node1.localPosition.y, node1.localPosition.z + 0.9113507999980357f)
        node35.setParent(anchorNode)
        node35.renderable = buildingRenderable35
        //node.select()
        node35.setParent(anchorNode)
/*
        val node36 = TransformableNode(arFragment.transformationSystem)
        node36.scaleController.minScale = 0.01f
        node36.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node36.localPosition = Vector3(node1.localPosition.x + -1.1924868000000062f, node1.localPosition.y, node1.localPosition.z + 0.728247599999304f)
        node36.setParent(anchorNode)
        node36.renderable = buildingRenderable36
        //node.select()
        node36.setParent(anchorNode)

        val node37 = TransformableNode(arFragment.transformationSystem)
        node37.scaleController.minScale = 0.01f
        node37.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node37.localPosition = Vector3(node1.localPosition.x + -1.1740332000001217f, node1.localPosition.y, node1.localPosition.z + 0.5289481999992859f)
        node37.setParent(anchorNode)
        node37.renderable = buildingRenderable37
        //node.select()
        node37.setParent(anchorNode)

        val node38 = TransformableNode(arFragment.transformationSystem)
        node38.scaleController.minScale = 0.01f
        node38.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node38.localPosition = Vector3(node1.localPosition.x + -2.3707440000000135f, node1.localPosition.y, node1.localPosition.z + 0.1794501999975182f)
        node38.setParent(anchorNode)
        node38.renderable = buildingRenderable38
        //node.select()
        node38.setParent(anchorNode)

        val node39 = TransformableNode(arFragment.transformationSystem)
        node39.scaleController.minScale = 0.01f
        node39.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node39.localPosition = Vector3(node1.localPosition.x + -2.2436746000001224f, node1.localPosition.y, node1.localPosition.z + 0.1369059999997262f)
        node39.setParent(anchorNode)
        node39.renderable = buildingRenderable39
        //node.select()
        node39.setParent(anchorNode)

        val node40 = TransformableNode(arFragment.transformationSystem)
        node40.scaleController.minScale = 0.01f
        node40.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node40.localPosition = Vector3(node1.localPosition.x + -2.09005159999997f, node1.localPosition.y, node1.localPosition.z + 0.16721819999802393f)
        node40.setParent(anchorNode)
        node40.renderable = buildingRenderable40
        //node.select()
        node40.setParent(anchorNode)

        val node41 = TransformableNode(arFragment.transformationSystem)
        node41.scaleController.minScale = 0.01f
        node41.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node41.localPosition = Vector3(node1.localPosition.x + -2.2164060000000974f, node1.localPosition.y, node1.localPosition.z + 0.2027131999988342f)
        node41.setParent(anchorNode)
        node41.renderable = buildingRenderable41
        //node.select()
        node41.setParent(anchorNode)

        val node42 = TransformableNode(arFragment.transformationSystem)
        node42.scaleController.minScale = 0.01f
        node42.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node42.localPosition = Vector3(node1.localPosition.x + -0.544696600000134f, node1.localPosition.y, node1.localPosition.z + 1.3770957999979148f)
        node42.setParent(anchorNode)
        node42.renderable = buildingRenderable42
        //node.select()
        node42.setParent(anchorNode)

        val node43 = TransformableNode(arFragment.transformationSystem)
        node43.scaleController.minScale = 0.01f
        node43.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node43.localPosition = Vector3(node1.localPosition.x + -2.317382800000087f, node1.localPosition.y, node1.localPosition.z + -0.11204540000180714f)
        node43.setParent(anchorNode)
        node43.renderable = buildingRenderable43
        //node.select()
        node43.setParent(anchorNode)*/

/*
        val node45 = TransformableNode(arFragment.transformationSystem)
        node45.scaleController.minScale = 0.01f
        node45.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node45.localPosition = Vector3(node1.localPosition.x + -2.33173740000002f, node1.localPosition.y, node1.localPosition.z + -0.010054200000013225f)
        node45.setParent(anchorNode)
        node45.renderable = buildingRenderable45
        //node.select()
        node45.setParent(anchorNode)

        val node46 = TransformableNode(arFragment.transformationSystem)
        node46.scaleController.minScale = 0.01f
        node46.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node46.localPosition = Vector3(node1.localPosition.x + -2.433615800000007f, node1.localPosition.y, node1.localPosition.z + 1.364072599998326f)
        node46.setParent(anchorNode)
        node46.renderable = buildingRenderable46
        //node.select()
        node46.setParent(anchorNode)

        val node47 = TransformableNode(arFragment.transformationSystem)
        node47.scaleController.minScale = 0.01f
        node47.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node47.localPosition = Vector3(node1.localPosition.x + -1.860836399999971f, node1.localPosition.y, node1.localPosition.z + 0.08091519999725279f)
        node47.setParent(anchorNode)
        node47.renderable = buildingRenderable47
        //node.select()
        node47.setParent(anchorNode)

        val node48 = TransformableNode(arFragment.transformationSystem)
        node48.scaleController.minScale = 0.01f
        node48.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node48.localPosition = Vector3(node1.localPosition.x + -1.0309846000000107f, node1.localPosition.y, node1.localPosition.z + -0.11352020000049379f)
        node48.setParent(anchorNode)
        node48.renderable = buildingRenderable48
        //node.select()
        node48.setParent(anchorNode)

        val node49 = TransformableNode(arFragment.transformationSystem)
        node49.scaleController.minScale = 0.01f
        node49.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node49.localPosition = Vector3(node1.localPosition.x + -1.5809434000000693f, node1.localPosition.y, node1.localPosition.z + -0.17807120000070428f)
        node49.setParent(anchorNode)
        node49.renderable = buildingRenderable49
        //node.select()
        node49.setParent(anchorNode)

        val node50 = TransformableNode(arFragment.transformationSystem)
        node50.scaleController.minScale = 0.01f
        node50.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node50.localPosition = Vector3(node1.localPosition.x + -1.7651687999999923f, node1.localPosition.y, node1.localPosition.z + -0.11748400000215042f)
        node50.setParent(anchorNode)
        node50.renderable = buildingRenderable50
        //node.select()
        node50.setParent(anchorNode)

        val node51 = TransformableNode(arFragment.transformationSystem)
        node51.scaleController.minScale = 0.01f
        node51.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node51.localPosition = Vector3(node1.localPosition.x + -1.6862974000001487f, node1.localPosition.y, node1.localPosition.z + -0.16920500000123867f)
        node51.setParent(anchorNode)
        node51.renderable = buildingRenderable51
        //node.select()
        node51.setParent(anchorNode)

        val node52 = TransformableNode(arFragment.transformationSystem)
        node52.scaleController.minScale = 0.01f
        node52.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node52.localPosition = Vector3(node1.localPosition.x + -1.6202902000000905f, node1.localPosition.y, node1.localPosition.z + -0.08880140000255779f)
        node52.setParent(anchorNode)
        node52.renderable = buildingRenderable52
        //node.select()
        node52.setParent(anchorNode)

        val node53 = TransformableNode(arFragment.transformationSystem)
        node53.scaleController.minScale = 0.01f
        node53.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node53.localPosition = Vector3(node1.localPosition.x + -0.3910624000000098f, node1.localPosition.y, node1.localPosition.z + 0.6018655999971089f)
        node53.setParent(anchorNode)
        node53.renderable = buildingRenderable53
        //node.select()
        node53.setParent(anchorNode)

        val node54 = TransformableNode(arFragment.transformationSystem)
        node54.scaleController.minScale = 0.01f
        node54.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node54.localPosition = Vector3(node1.localPosition.x + -1.373264399999971f, node1.localPosition.y, node1.localPosition.z + 0.6757015999988654f)
        node54.setParent(anchorNode)
        node54.renderable = buildingRenderable54
        //node.select()
        node54.setParent(anchorNode)

        val node55 = TransformableNode(arFragment.transformationSystem)
        node55.scaleController.minScale = 0.01f
        node55.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node55.localPosition = Vector3(node1.localPosition.x + 0.036273599999913135f, node1.localPosition.y, node1.localPosition.z + 0.5002335999975912f)
        node55.setParent(anchorNode)
        node55.renderable = buildingRenderable55
        //node.select()
        node55.setParent(anchorNode)

        val node56 = TransformableNode(arFragment.transformationSystem)
        node56.scaleController.minScale = 0.01f
        node56.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node56.localPosition = Vector3(node1.localPosition.x + -1.308070399999997f, node1.localPosition.y, node1.localPosition.z + 0.21616159999975937f)
        node56.setParent(anchorNode)
        node56.renderable = buildingRenderable56
        //node.select()
        node56.setParent(anchorNode)

        val node57 = TransformableNode(arFragment.transformationSystem)
        node57.scaleController.minScale = 0.01f
        node57.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node57.localPosition = Vector3(node1.localPosition.x + -2.2089744000000793f, node1.localPosition.y, node1.localPosition.z + 0.0512055999977747f)
        node57.setParent(anchorNode)
        node57.renderable = buildingRenderable57
        //node.select()
        node57.setParent(anchorNode)

        val node58 = TransformableNode(arFragment.transformationSystem)
        node58.scaleController.minScale = 0.01f
        node58.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node58.localPosition = Vector3(node1.localPosition.x + -0.47363640000003215f, node1.localPosition.y, node1.localPosition.z + 1.283677599998191f)
        node58.setParent(anchorNode)
        node58.renderable = buildingRenderable58
        //node.select()
        node58.setParent(anchorNode)

        val node59 = TransformableNode(arFragment.transformationSystem)
        node59.scaleController.minScale = 0.01f
        node59.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node59.localPosition = Vector3(node1.localPosition.x + -1.9680344000000334f, node1.localPosition.y, node1.localPosition.z + 0.9172015999996802f)
        node59.setParent(anchorNode)
        node59.renderable = buildingRenderable59
        //node.select()
        node59.setParent(anchorNode)

        val node60 = TransformableNode(arFragment.transformationSystem)
        node60.scaleController.minScale = 0.01f
        node60.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node60.localPosition = Vector3(node1.localPosition.x + -2.125824400000056f, node1.localPosition.y, node1.localPosition.z + 0.37339959999953865f)
        node60.setParent(anchorNode)
        node60.renderable = buildingRenderable60
        //node.select()
        node60.setParent(anchorNode)

        val node61 = TransformableNode(arFragment.transformationSystem)
        node61.scaleController.minScale = 0.01f
        node61.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node61.localPosition = Vector3(node1.localPosition.x + -2.260702400000082f, node1.localPosition.y, node1.localPosition.z + 0.30482159999955916f)
        node61.setParent(anchorNode)
        node61.renderable = buildingRenderable61
        //node.select()
        node61.setParent(anchorNode)

        val node62 = TransformableNode(arFragment.transformationSystem)
        node62.scaleController.minScale = 0.01f
        node62.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node62.localPosition = Vector3(node1.localPosition.x + -2.029348399999981f, node1.localPosition.y, node1.localPosition.z + 0.8257175999984612f)
        node62.setParent(anchorNode)
        node62.renderable = buildingRenderable62
        //node.select()
        node62.setParent(anchorNode)

        val node63 = TransformableNode(arFragment.transformationSystem)
        node63.scaleController.minScale = 0.01f
        node63.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node63.localPosition = Vector3(node1.localPosition.x + -0.29866240000010297f, node1.localPosition.y, node1.localPosition.z + 0.5843615999998292f)
        node63.setParent(anchorNode)
        node63.renderable = buildingRenderable63
        //node.select()
        node63.setParent(anchorNode)

        val node64 = TransformableNode(arFragment.transformationSystem)
        node64.scaleController.minScale = 0.01f
        node64.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node64.localPosition = Vector3(node1.localPosition.x + -2.2624504000001253f, node1.localPosition.y, node1.localPosition.z + -0.07073040000104812f)
        node64.setParent(anchorNode)
        node64.renderable = buildingRenderable64
        //node.select()
        node64.setParent(anchorNode)

        val node65 = TransformableNode(arFragment.transformationSystem)
        node65.scaleController.minScale = 0.01f
        node65.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node65.localPosition = Vector3(node1.localPosition.x + -2.1995544000001246f, node1.localPosition.y, node1.localPosition.z + -0.050974400000995956f)
        node65.setParent(anchorNode)
        node65.renderable = buildingRenderable65
        //node.select()
        node65.setParent(anchorNode)

        val node66 = TransformableNode(arFragment.transformationSystem)
        node66.scaleController.minScale = 0.01f
        node66.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node66.localPosition = Vector3(node1.localPosition.x + -1.707382400000097f, node1.localPosition.y, node1.localPosition.z + 1.3336075999977766f)
        node66.setParent(anchorNode)
        node66.renderable = buildingRenderable66
        //node.select()
        node66.setParent(anchorNode)

        val node67 = TransformableNode(arFragment.transformationSystem)
        node67.scaleController.minScale = 0.01f
        node67.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node67.localPosition = Vector3(node1.localPosition.x + -0.3262244000001374f, node1.localPosition.y, node1.localPosition.z + 0.6386375999980374f)
        node67.setParent(anchorNode)
        node67.renderable = buildingRenderable67
        //node.select()
        node67.setParent(anchorNode)

        val node68 = TransformableNode(arFragment.transformationSystem)
        node68.scaleController.minScale = 0.01f
        node68.localScale = Vector3(0.2f, 0.2f, 0.2f)
        node68.localPosition = Vector3(node1.localPosition.x + -2.0675564000001034f, node1.localPosition.y, node1.localPosition.z + 0.28791159999964294f)
        node68.setParent(anchorNode)
        node68.renderable = buildingRenderable68
        //node.select()
        node68.setParent(anchorNode)*/

    }

    private fun createReichsTagRenderable() {
        // reichstag
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e00964d4f.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable ->
                    reichstag = renderable
                    runOnUiThread {
                        createModelRenderables()
                    }

                }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $reichstag.", throwable)
                    Log.e(TAG, throwable.message)
                    Log.e(TAG, throwable.stackTrace.joinToString { it.toString() + "\n" })
                    // restart activity
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("assetsDir", assetsDirString)
                    }
                    finish()
                    startActivity(intent)
                    null
                }
    }

    private fun createModelRenderables() {
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000a000850f8.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable1 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable1.", throwable)
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("assetsDir", assetsDirString)
                    }
                    finish()
                    startActivity(intent)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_000300000018195f.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable2 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable2.", throwable)
                    null
                }/*
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000000181d2b.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable3 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable3.", throwable)
                    null
                }

        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000000181f6e.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable4 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable4.", throwable)
                    null
                }

        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000000182921.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable5 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable5.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_00030000001829f9.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable6 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable6.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_000300020018615e.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable7 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable7.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_000300020053d937.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable8 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable8.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000400019869.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable9 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable9.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000400019874.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable10 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable10.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_000300040001988a.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable11 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable11.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000800154a60.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable12 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable12.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_00030009000dbcdd.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable13 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable13.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_00030009000dbcf3.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable14 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable14.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_00030009003f2b54.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable15 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable15.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_00030009003f2b5e.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable16 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable16.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_00030009003f2bb8.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable17 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable17.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_00030009003f2bbe.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable18 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable18.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000900a33f5f.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable19 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable19.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000a000850f8.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable20 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable20.", throwable)
                    null
                }*/
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000a00085101.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable21 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable21.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000a001c95e0.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable22 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable22.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000b00348962.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable23 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable23.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000b006f8d79.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable24 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable24.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000b006f8d7f.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable25 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable25.", throwable)
                    null
                }/*
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000b006f8da5.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable26 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable26.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000b006f8da9.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable27 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable27.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000b006f8dab.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable28 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable28.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e00143724.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable29 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable29.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064fe96.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable30 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable30.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064fea3.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable31 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable31.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064feae.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable32 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable32.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064feb0.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable33 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable33.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064feb2.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable34 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable34.", throwable)
                    null
                }*/
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064feb7.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable35 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable35.", throwable)
                    null
                }/*
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064fec9.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable36 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable36.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0064fecd.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable37 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable37.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e006e61dd.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable38 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable38.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e006e61e0.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable39 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable39.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e006e61e2.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable40 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable40.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e006e61e4.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable41 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable41.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e0087c0ab.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable42 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable42.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000e00964cf5.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable43 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable43.", throwable)
                    null
                }*/
/*
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f000043fe.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable45 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable45.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f0001a247.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable46 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable46.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f0010cb54.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable47 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable47.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f001c67ac.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable48 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable48.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f00294f03.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable49 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable49.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f00294f08.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable50 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable50.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f00294f0a.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable51 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable51.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/BLDG_0003000f00294f13.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable52 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable52.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_058eef78-5062-425c-a939-ab844aa2b3df.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable53 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable53.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_18bafe1f-94d4-46e6-91f6-990eaaf9d508.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable54 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable54.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_4430be2c-322e-4e86-846e-6c112ba3f44c.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable55 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable55.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_50aa6e34-e12a-42be-bc21-ed483bf75910.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable56 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable56.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_54749125-76be-4e4f-8524-f85ca8b6b0a0.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable57 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable57.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_5e4c0dbc-7dc9-4f16-9b1e-44def4460884.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable58 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable58.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_680e9ace-7a27-42eb-b1f7-9b2d26e43742.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable59 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable59.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_6e460375-693f-43e8-91c8-ca7d2355e1d0.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable60 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable60.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_a0b18fb8-0f16-4781-9c8e-17cb1b3fc025.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable61 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable61.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_a239090f-016c-4e37-8359-356c346f19c1.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable62 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable62.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_bc1738f3-e737-44d4-a3d3-127f3ab1637c.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable63 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable63.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_da36b353-edb7-4c92-b12e-eea92c2dc54a.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable64 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable64.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_e0cd7c66-f069-4f5c-a8d7-2572c1d3d715.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable65 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable65.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_e3988055-f813-47ee-bae1-c49b1d0f25cb.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable66 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable66.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_e7031f19-bc98-4214-8d1a-d53411d90034.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable67 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable67.", throwable)
                    null
                }
        ModelRenderable.builder()
                // To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
                .setSource(this, Uri.parse("file://$assetsDirString/DEB_LOD2_UUID_e8c3aa22-7725-4e65-a6f0-befcca9c6193.sfb"))

                // Instead, load as a resource from the 'res/raw' folder ('src/main/res/raw/andy.sfb'):
                //.setSource(this, R.raw.andy)

                .build()
                .thenAccept { renderable -> buildingRenderable68 = renderable }
                .exceptionally { throwable ->
                    Log.e(TAG, "Unable to load Renderable $buildingRenderable68.", throwable)
                    null
                }*/
    }
}
