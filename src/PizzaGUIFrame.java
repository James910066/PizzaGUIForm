import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PizzaGUIFrame extends JFrame
{
    String crustType = "";
    int toppingCnt = 0;
    String crustSize = "";
    double totalCost = 0.0;
    double toppingCost = 0.0;
    double subTotal = 0.0;
    double taxAmount = .07;
    double taxTotal = 0.0;
    double crustCost = 0.0;
    JPanel mainPnl;
    JPanel sizePnl;
    JPanel toppingsPnl;
    JPanel locationPnl;
    JPanel controlPnl;
    JPanel crustPanel;
    JPanel receiptPnl; //this panel displays the receipt
    JTextArea receiptTA;
    JScrollPane scroller;
    JOptionPane quitConfirmPane;

    JComboBox homeCB;

    JRadioButton smallRB; //$8.00
    JRadioButton mediumRB; //$12.00
    JRadioButton largeRB; //$16.00
    JRadioButton superRB; //$20.00

    JRadioButton thinRB; //Thin crust
    JRadioButton regularRB; //Regular crust
    JRadioButton deepRB; //Deep Dish crust

    //Road Kill toppings //$1.00 extra for each
    JCheckBox ratCB;
    JCheckBox deerCB;
    JCheckBox possumCB;
    JCheckBox groundHogCB;
    JCheckBox squirrelCB;
    JCheckBox rabbitCB;

    //Control panel buttons
    JButton quitBtn;
    JButton displayChoicesBtn;
    JButton orderBtn;
    JButton clearBtn;

    //Quit confirmation buttons
//    JButton yesBtn;
//    JButton noBtn;

    public PizzaGUIFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(6, 1));

        createPizzaSizePnl();
        mainPnl.add(sizePnl);

        createCrustPanel();
        mainPnl.add(crustPanel);

        createToppingsPnl();
        mainPnl.add(toppingsPnl);

        createLocationPnl();
        mainPnl.add(locationPnl);

        createReceiptPnl();
        mainPnl.add(receiptPnl);

        createControlPanel();
        mainPnl.add(controlPnl);

        add(mainPnl);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createPizzaSizePnl()
    {
        sizePnl = new JPanel();
        sizePnl.setLayout(new GridLayout(1, 4));
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(),"Pizza Sizes"));

        smallRB  = new JRadioButton("Small");
        mediumRB = new JRadioButton("Medium");
        largeRB  = new JRadioButton("Large");
        superRB  = new JRadioButton("Super");

        sizePnl.add(smallRB);
        sizePnl.add(mediumRB);
        sizePnl.add(largeRB);
        sizePnl.add(superRB);

        ButtonGroup group = new ButtonGroup();
        group.add(smallRB);
        group.add(mediumRB);
        group.add(largeRB);
        group.add(superRB);
        smallRB.setSelected(true);
    }

    private void createCrustPanel()
    {
        crustPanel = new JPanel();
        crustPanel.setLayout(new GridLayout(1, 3));
        crustPanel.setBorder(new TitledBorder(new EtchedBorder(),"Crust Choices"));

        thinRB = new JRadioButton("Thin");
        regularRB = new JRadioButton("Regular");
        deepRB = new JRadioButton("Deep Dish");

        crustPanel.add(thinRB);
        crustPanel.add(regularRB);
        crustPanel.add(deepRB);
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinRB);
        crustGroup.add(regularRB);
        crustGroup.add(deepRB);
        thinRB.setSelected(true);
    }
    private void createToppingsPnl()
    {
        toppingsPnl = new JPanel();
        toppingsPnl.setLayout(new GridLayout(2,3));
        toppingsPnl.setBorder(new TitledBorder(new EtchedBorder(),"Toppings"));

        deerCB = new JCheckBox("Bambi Bits");
        possumCB = new JCheckBox("Possum Parts");
        groundHogCB = new JCheckBox("GroundHog Goodies");
        squirrelCB = new JCheckBox("Squirrly Pieces");
        rabbitCB = new JCheckBox("Thumper Bites");
        ratCB = new JCheckBox("Roasted Rat Chunks");

        toppingsPnl.add(deerCB);
        toppingsPnl.add(possumCB);
        toppingsPnl.add(groundHogCB);
        toppingsPnl.add(squirrelCB);
        toppingsPnl.add(rabbitCB);
        toppingsPnl.add(ratCB);
    }
    private void createLocationPnl()
    {
        locationPnl = new JPanel();
        locationPnl.setBorder(new TitledBorder(new EtchedBorder(),"Location"));

        homeCB = new JComboBox();
        homeCB.addItem("OutBack");
        homeCB.addItem("Desert");
        homeCB.addItem("Amazon");
        homeCB.addItem("Anartica");
        homeCB.addItem("BooneDocks");
        locationPnl.add(homeCB);
    }

    private void createReceiptPnl()
    {
        receiptPnl = new JPanel();
        receiptPnl.setBorder(new TitledBorder(new EtchedBorder(),("Receipt")));
        receiptTA = new JTextArea(15,75);
        receiptTA.setEditable(false);
        scroller = new JScrollPane(receiptTA);
        receiptPnl.add(scroller);
    }

    private void createControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setBorder(new TitledBorder(new EtchedBorder(),"Controls"));

        displayChoicesBtn = new JButton("Review Order");
        displayChoicesBtn.addActionListener(
                (ActionEvent ae) ->
                {   // Generate a result string and then display it with a Message Dialog
                    String res ="Form Details\n";
                    // Pizza size
                    res += "Crust size: ";
                    if(smallRB.isSelected())
                        res += "Small\n";
                    else if(mediumRB.isSelected())
                        res += "Medium\n";
                    else if(largeRB.isSelected())
                        res += "Large\n";
                    else if(superRB.isSelected())
                        res += "Super\n";

                    //Crust choices
                    res += "Crust Type is:\n";
                    if (thinRB.isSelected())
                        res += "Thin\n";
                    if (regularRB.isSelected())
                        res += "Regular\n";
                    if (deepRB.isSelected())
                        res += "Deep Dish\n";

                    // Toppings
                    res += "With these gourmet toppings:\n";
                    if (deerCB.isSelected())
                        res += "\tBambi Bits\n";
                    toppingCnt = toppingCnt + 1;
                    if (possumCB.isSelected())
                        res += "\tPossum Parts\n";
                    toppingCnt = toppingCnt + 1;
                    if (groundHogCB.isSelected())
                        res += "\tGroundHog Goodies\n";
                    toppingCnt = toppingCnt + 1;
                    if (squirrelCB.isSelected())
                        res += "\tSquirrly Pieces\n";
                    toppingCnt = toppingCnt + 1;
                    if (rabbitCB.isSelected())
                        res += "\tThumper Bites\n";
                    toppingCnt = toppingCnt + 1;
                    if (ratCB.isSelected())
                        res += "\tRoasted Rat Chunks\n";
                    toppingCnt = toppingCnt + 1;
                    res += "To be delivered to: ";
                    res += (String) homeCB.getSelectedItem();
                    res += "\n";
                    // Display the choices
                    JOptionPane.showMessageDialog(mainPnl,res);
                }
        );
        //Placing order and showing receipt in scroll pane
        orderBtn = new JButton("Place Order");
        orderBtn.addActionListener((ActionEvent ae) ->
        {
            DecimalFormat df = new DecimalFormat("0.00");
        if(smallRB.isSelected())
        {
            crustSize += "Small";
            crustCost = 8.00;
        }
        else if(mediumRB.isSelected())
        {
            //crustCost = crustCost + mediumSize;
            crustSize += "Medium";
            crustCost = 12.00;
        }
        else if(largeRB.isSelected())
        {
            //crustCost = crustCost + largeSize;
            crustSize += "Large";
            crustCost = 16.00;
        }
        else if(superRB.isSelected())
        {
            //crustCost = crustCost + superSize;
            crustSize += "Super Size";
            crustCost = 20.00;
        }
        //Crust type
            if(thinRB.isSelected())
                crustType = "Thin";
            else if (regularRB.isSelected())
            {
                crustType = "Regular";
            }
            else
            {
                crustType = "Deep Dish";
            }
            //Calculate toppings cost
            if (deerCB.isSelected())
                toppingCnt = toppingCnt + 1;
            if (possumCB.isSelected())
                toppingCnt = toppingCnt + 1;
            if (groundHogCB.isSelected())
                toppingCnt = toppingCnt + 1;
            if (squirrelCB.isSelected())
                toppingCnt = toppingCnt + 1;
            if (rabbitCB.isSelected())
                toppingCnt = toppingCnt + 1;
            if (ratCB.isSelected())
                toppingCnt = toppingCnt + 1;

            toppingCost = toppingCnt * 1.00;
            subTotal = crustCost + toppingCost;
            taxTotal = subTotal * taxAmount;
            totalCost = subTotal + taxTotal;
            //Format the dollar amounts
            String toppingCostFM = df.format(toppingCost);
            String crustCostFM = df.format(crustCost);
            String subTotalFM = df.format(subTotal);
            String taxTotalFM = df.format(taxTotal);
            String totalCostFM = df.format(totalCost);

            //BUILD THE RECEIPT
            receiptTA.append("==================================================================================\n");
            receiptTA.append(crustType + " " + crustSize + " crust\t\t\t\t\t" + "$" + crustCostFM + "\n");
            receiptTA.append("Ingredients\t\t\t\t\t\t" + "$" + toppingCostFM + "\n\n");
            receiptTA.append("Sub-total:\t\t\t\t\t\t" + "$" + subTotalFM + "\n");
            receiptTA.append("Tax:\t\t\t\t\t\t" + "$" + taxTotalFM + "\n");

            receiptTA.append("--------------------------------------------------------------------------------------------------------------------------------------\n");
            receiptTA.append("Total:\t\t\t\t\t\t" + "$" + totalCostFM + "\n");
            receiptTA.append("==================================================================================");
    });
        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            Scanner in = new Scanner(System.in);
            boolean quiting = false;
            String response = "[Y/N]";
            JOptionPane.showMessageDialog(mainPnl,response);
            quiting = SafeInput.getYNConfirm(in,"Are you Done?");
            if(quiting == false)
            {
                System.exit((0));
            }
        });
        //Clear the ingredients and receipt form or Scroll pane
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent ae) ->
        {
            ratCB.setSelected(false);
            deerCB.setSelected(false);
            possumCB.setSelected(false);
            groundHogCB.setSelected(false);
            squirrelCB.setSelected(false);
            rabbitCB.setSelected(false);
            smallRB.setSelected(false);
            mediumRB.setSelected(false);
            largeRB.setSelected(false);
            superRB.setSelected(false);
            thinRB.setSelected(false);
            regularRB.setSelected(false);
            deepRB.setSelected(false);
            receiptTA.setText("");
        });
        controlPnl.add(orderBtn);
        controlPnl.add(displayChoicesBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);
    }
}