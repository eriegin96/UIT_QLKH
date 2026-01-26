package Util;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for applying consistent theme colors and styles across the application
 */
public class ThemeUtil {

    // Theme Colors
    public static final Color PRIMARY_COLOR = new Color(33, 150, 243);      // Blue
    public static final Color SUCCESS_COLOR = new Color(0, 202, 78);        // Green
    public static final Color WARNING_COLOR = new Color(255, 189, 68);      // Orange/Yellow
    public static final Color DANGER_COLOR = new Color(255, 96, 92);        // Red
    public static final Color SECONDARY_COLOR = new Color(100, 181, 246);   // Bright Light Blue
    public static final Color LOGOUT_COLOR = new Color(255, 167, 196);      // Light Pink
    public static final Color REFRESH_COLOR = new Color(255, 255, 255);     // Pure White

    // Text Colors
    public static final Color TEXT_PRIMARY = new Color(51, 51, 51);         // Dark Gray
    public static final Color TEXT_SECONDARY = new Color(108, 117, 125);    // Medium Gray
    public static final Color TEXT_WHITE = new Color(255, 255, 255);        // White

    // Background Colors
    public static final Color BACKGROUND_LIGHT = new Color(240, 242, 245);  // Light Gray-Blue
    public static final Color BACKGROUND_WHITE = new Color(255, 255, 255);  // Pure White
    public static final Color BACKGROUND_DARK = new Color(52, 58, 64);      // Dark Gray

    // Font Styles
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 24);

    /**
     * Applies primary button style (blue background)
     * 
     * @param button The JButton to style
     */
    public static void applyPrimaryButtonStyle(JButton button) {
        applyButtonStyle(button, PRIMARY_COLOR, TEXT_WHITE);
    }

    /**
     * Applies success button style (green background) - typically for Add/Save actions
     * 
     * @param button The JButton to style
     */
    public static void applySuccessButtonStyle(JButton button) {
        applyButtonStyle(button, SUCCESS_COLOR, TEXT_WHITE);
    }

    /**
     * Applies warning button style (orange background) - typically for Edit/Update actions
     * 
     * @param button The JButton to style
     */
    public static void applyWarningButtonStyle(JButton button) {
        applyButtonStyle(button, WARNING_COLOR, TEXT_PRIMARY);
    }

    /**
     * Applies danger button style (red background) - typically for Delete/Cancel actions
     * 
     * @param button The JButton to style
     */
    public static void applyDangerButtonStyle(JButton button) {
        applyButtonStyle(button, DANGER_COLOR, TEXT_WHITE);
    }

    /**
     * Applies secondary button style (bright blue background) - typically for neutral actions
     * 
     * @param button The JButton to style
     */
    public static void applySecondaryButtonStyle(JButton button) {
        applyButtonStyle(button, SECONDARY_COLOR, TEXT_WHITE);
    }

    /**
     * Applies logout button style (light pink background) - typically for logout actions
     * 
     * @param button The JButton to style
     */
    public static void applyLogoutButtonStyle(JButton button) {
        applyButtonStyle(button, LOGOUT_COLOR, TEXT_PRIMARY);
    }

    /**
     * Applies refresh button style (white background with border) - typically for refresh actions
     * 
     * @param button The JButton to style
     */
    public static void applyRefreshButtonStyle(JButton button) {
        applyButtonStyle(button, REFRESH_COLOR, TEXT_PRIMARY);
        // Add border with padding for refresh button
        button.setBorderPainted(true);
        javax.swing.border.Border lineBorder = javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        javax.swing.border.Border emptyBorder = javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10);
        button.setBorder(javax.swing.BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
    }

    /**
     * Applies a custom button style with specified colors
     * 
     * @param button          The JButton to style
     * @param backgroundColor The background color
     * @param textColor       The text color
     */
    public static void applyButtonStyle(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(FONT_BOLD);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add click effect automatically
        applyButtonClickEffect(button, backgroundColor);
    }
    
    /**
     * Applies click/press effect to a button
     * The button will darken when pressed and return to original color when released
     * 
     * @param button          The JButton to apply effect to
     * @param originalColor   The original background color
     */
    public static void applyButtonClickEffect(JButton button, Color originalColor) {
        final Color hoverColor = lightenColor(originalColor, 0.1f);
        final Color pressedColor = darkenColor(originalColor, 0.15f);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(hoverColor);
                }
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(originalColor);
                }
            }
            
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(pressedColor);
                }
            }
            
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    // Return to hover color if mouse is still over button, otherwise original
                    if (button.contains(evt.getPoint())) {
                        button.setBackground(hoverColor);
                    } else {
                        button.setBackground(originalColor);
                    }
                }
            }
        });
    }

    /**
     * Applies standard text field style
     * 
     * @param textField The JTextField to style
     */
    public static void applyTextFieldStyle(JTextField textField) {
        textField.setFont(FONT_REGULAR);
        textField.setForeground(TEXT_PRIMARY);
    }

    /**
     * Applies standard password field style
     * 
     * @param passwordField The JPasswordField to style
     */
    public static void applyPasswordFieldStyle(JPasswordField passwordField) {
        passwordField.setFont(FONT_REGULAR);
        passwordField.setForeground(TEXT_PRIMARY);
    }

    /**
     * Applies standard text area style
     * 
     * @param textArea The JTextArea to style
     */
    public static void applyTextAreaStyle(JTextArea textArea) {
        textArea.setFont(FONT_REGULAR);
        textArea.setForeground(TEXT_PRIMARY);
        textArea.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    /**
     * Applies standard label style
     * 
     * @param label The JLabel to style
     */
    public static void applyLabelStyle(JLabel label) {
        label.setFont(FONT_REGULAR);
        label.setForeground(TEXT_PRIMARY);
    }

    /**
     * Applies title label style (larger, bold)
     * 
     * @param label The JLabel to style
     */
    public static void applyTitleLabelStyle(JLabel label) {
        label.setFont(FONT_TITLE);
        label.setForeground(TEXT_PRIMARY);
    }

    /**
     * Applies header label style (largest, bold)
     * 
     * @param label The JLabel to style
     */
    public static void applyHeaderLabelStyle(JLabel label) {
        label.setFont(FONT_HEADER);
        label.setForeground(TEXT_PRIMARY);
    }

    /**
     * Applies light background to a component
     * 
     * @param component The component to style
     */
    public static void applyLightBackground(JComponent component) {
        component.setBackground(BACKGROUND_LIGHT);
    }

    /**
     * Applies white background to a component
     * 
     * @param component The component to style
     */
    public static void applyWhiteBackground(JComponent component) {
        component.setBackground(BACKGROUND_WHITE);
    }

    /**
     * Applies dark background to a component
     * 
     * @param component The component to style
     */
    public static void applyDarkBackground(JComponent component) {
        component.setBackground(BACKGROUND_DARK);
    }

    /**
     * Applies standard panel style
     * 
     * @param panel The JPanel to style
     */
    public static void applyPanelStyle(JPanel panel) {
        panel.setBackground(BACKGROUND_WHITE);
    }

    /**
     * Applies table style for better appearance
     * 
     * @param table The JTable to style
     */
    public static void applyTableStyle(JTable table) {
        table.setFont(FONT_REGULAR);
        table.setRowHeight(25);
        table.setForeground(TEXT_PRIMARY);
        table.setBackground(BACKGROUND_WHITE);
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setGridColor(new Color(220, 220, 220));
        
        // Style table header
        if (table.getTableHeader() != null) {
            table.getTableHeader().setFont(FONT_BOLD);
            table.getTableHeader().setBackground(BACKGROUND_LIGHT);
            table.getTableHeader().setForeground(TEXT_PRIMARY);
        }
    }

    /**
     * Applies combo box style
     * 
     * @param comboBox The JComboBox to style
     */
    public static void applyComboBoxStyle(JComboBox<?> comboBox) {
        comboBox.setFont(FONT_REGULAR);
        comboBox.setForeground(TEXT_PRIMARY);
        comboBox.setBackground(BACKGROUND_WHITE);
    }

    /**
     * Applies date picker style (for JDateChooser)
     * 
     * @param dateChooser The JDateChooser to style
     */
    public static void applyDatePickerStyle(com.toedter.calendar.JDateChooser dateChooser) {
        dateChooser.setFont(FONT_REGULAR);
        dateChooser.setForeground(TEXT_PRIMARY);
        dateChooser.setBackground(BACKGROUND_WHITE);
        
        // Style the internal text field
        if (dateChooser.getDateEditor() != null && dateChooser.getDateEditor().getUiComponent() instanceof JTextField) {
            JTextField textField = (JTextField) dateChooser.getDateEditor().getUiComponent();
            textField.setFont(FONT_REGULAR);
            textField.setForeground(TEXT_PRIMARY);
            textField.setBackground(BACKGROUND_WHITE);
        }
    }

    /**
     * Applies a hover effect to a button (call this in mouse listeners)
     * 
     * @param button     The button to apply hover to
     * @param hoverColor The color when hovering
     */
    public static void applyButtonHoverEffect(JButton button, Color hoverColor) {
        Color originalColor = button.getBackground();
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
    }

    /**
     * Creates a lighter version of a color (for hover effects)
     * 
     * @param color  The original color
     * @param factor The factor to lighten (0.0 to 1.0, typically 0.1 to 0.3)
     * @return The lightened color
     */
    public static Color lightenColor(Color color, float factor) {
        int r = Math.min(255, (int) (color.getRed() + (255 - color.getRed()) * factor));
        int g = Math.min(255, (int) (color.getGreen() + (255 - color.getGreen()) * factor));
        int b = Math.min(255, (int) (color.getBlue() + (255 - color.getBlue()) * factor));
        return new Color(r, g, b);
    }

    /**
     * Creates a darker version of a color (for hover effects)
     * 
     * @param color  The original color
     * @param factor The factor to darken (0.0 to 1.0, typically 0.1 to 0.3)
     * @return The darkened color
     */
    public static Color darkenColor(Color color, float factor) {
        int r = Math.max(0, (int) (color.getRed() * (1 - factor)));
        int g = Math.max(0, (int) (color.getGreen() * (1 - factor)));
        int b = Math.max(0, (int) (color.getBlue() * (1 - factor)));
        return new Color(r, g, b);
    }
}
