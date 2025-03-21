package com.github.adrienpessu.sarifviewer.configurable

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPasswordField
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JToggleButton


class SettingComponent {
    private var myMainPanel: JPanel? = null
    private var ghTokenTextField: JTextField =JBTextField()
    private var ghTokenPasswordField: JTextField = JBPasswordField()
    private val ghesHostnameText = JBTextField()
    private var ghesTokenTextField: JTextField  = JBTextField()
    private var ghesTokenPasswordField: JTextField  = JBPasswordField()
    private val toggleButton = JToggleButton("Show/Hide PAT")
    private val pathPrefixTextField = JBTextField() // New field for PathPrefix
    private val ruleIdDisplayToggle = JToggleButton("Enable/Disable Rule ID Display") // New toggle for Rule ID Display
    private var isRuleIdDisplayEnabled: Boolean = false // State for Rule ID Display

    private var isGhTokenVisible: Boolean = false

    init {

        ghTokenTextField.isVisible = isGhTokenVisible
        ghesTokenTextField.isVisible = isGhTokenVisible
        myMainPanel = FormBuilder.createFormBuilder()
            .addComponent(JBLabel("GitHub.com PAT "))
            .addComponent(ghTokenTextField)
            .addComponent(ghTokenPasswordField)
            .addSeparator(48)
            .addComponent(JBLabel("GHES Hostname "))
            .addComponent(ghesHostnameText)
            .addComponent(JBLabel("GHES PAT "))
            .addComponent(ghesTokenTextField)
            .addComponent(ghesTokenPasswordField)
            .addSeparator(48)
            .addComponent(JBLabel("Path Prefix ")) // Add label for PathPrefix
            .addComponent(pathPrefixTextField) // Add text field for PathPrefix
            .addSeparator(48)
            .addLabeledComponent("Rule ID Display: ", ruleIdDisplayToggle, 1, false) // Add toggle for Rule ID Display
            .addComponentFillVertically(JPanel(), 0)
            .addSeparator()
            .addLabeledComponent("", toggleButton, 1, false)
            .panel

        ruleIdDisplayToggle.addActionListener {
            isRuleIdDisplayEnabled = ruleIdDisplayToggle.isSelected // Update state based on toggle
            myMainPanel!!.revalidate() // Notify the layout manager
            myMainPanel!!.repaint() // Redraw the components
        }

        toggleButton.addActionListener {
            isGhTokenVisible = !isGhTokenVisible
            if (isGhTokenVisible) {
                ghTokenTextField.text = ghTokenPasswordField.text
                ghTokenTextField.isVisible = true
                ghTokenPasswordField.isVisible = false

                ghesTokenTextField.text = ghesTokenPasswordField.text
                ghesTokenTextField.isVisible = true
                ghesTokenPasswordField.isVisible = false
            } else {
                ghTokenPasswordField.text = ghTokenTextField.text
                ghTokenTextField.isVisible = false
                ghTokenPasswordField.isVisible = true

                ghesTokenPasswordField.text = ghesTokenTextField.text
                ghesTokenTextField.isVisible = false
                ghesTokenPasswordField.isVisible = true
            }
            myMainPanel!!.revalidate() // Notify the layout manager
            myMainPanel!!.repaint() // Redraw the components
        }
    }


    fun getPanel(): JPanel {
        return myMainPanel!!
    }

    fun getPreferredFocusedComponent(): JComponent {
        return if (toggleButton.isSelected) {
            ghTokenTextField
        } else {
            ghTokenPasswordField
        }
    }

    fun getGhTokenText(): String {
        return if (isGhTokenVisible) {
            ghTokenTextField.text
        } else {
            ghTokenPasswordField.text
        }
    }

    fun setGhTokenText(newText: String) {
        ghTokenTextField.text = newText
        ghTokenPasswordField.text = newText
    }

    fun getGhesHostnameText(): String {
        return ghesHostnameText.text
    }

    fun getGhesTokenText(): String {
        return if (isGhTokenVisible) {
            ghesTokenTextField.text
        } else {
            ghesTokenPasswordField.text
        }
    }

    fun setGhesHostnameText(newText: String) {
        ghesHostnameText.text = newText
    }

    fun setGhesTokenText(newText: String) {
        ghesTokenTextField.text = newText
        ghesTokenPasswordField.text = newText
    }

    fun getPathPrefixText(): String {
        return pathPrefixTextField.text // Getter for PathPrefix
    }

    fun setPathPrefixText(newText: String) {
        pathPrefixTextField.text = newText // Setter for PathPrefix
    }

    fun isRuleIdDisplayEnabled(): Boolean {
        return isRuleIdDisplayEnabled // Getter for Rule ID Display state
    }

    fun setRuleIdDisplayEnabled(enabled: Boolean) {
        isRuleIdDisplayEnabled = enabled
        ruleIdDisplayToggle.isSelected = enabled // Setter for Rule ID Display state
    }
}



