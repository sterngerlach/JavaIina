
/* ItemsPanel.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ItemsPanel extends PanelBase
{
    private static final long serialVersionUID = -1151867552844347246L;
    
    private MainModel mMainModel;
    
    private JCheckBox mCheckBoxEnableSearch;
    private JPanel mPanelSearch;
    private JPanel mPanelSearchCriteria;
    private JLabel mLabelSearchItemName;
    private JTextField mTextBoxSearchItemName;
    private JPanel mPanelSearchBottom;
    private JButton mButtonSearch;
    
    private JSplitPane mSplitPane;
    private JScrollPane mScrollPaneTreeView;
    private JTree mTreeViewRentalCategory;
    private DefaultMutableTreeNode mTreeViewRentalCategoryRootNode;
    private DefaultTreeModel mTreeModelRentalCategory;
    private JList<RentalObject> mListViewItems;
    private DefaultListModel<RentalObject> mListViewItemsModel;
    
    private JPanel mBottomPanel;
    private JButton mButtonBorrowSelectedItem;
    
    @Override
    public String getPanelName()
    {
        return "Items";
    }
    
    public ItemsPanel()
    {
    }
    
    public void setModel(MainModel mainModel)
    {
        this.mMainModel = mainModel;
    }
    
    @Override
    protected void initializeComponent()
    {
        super.initializeComponent();
        
        /* Header */
        this.setHeaderTitle("Items");
        
        /* Center Panel */
        this.mPanelCenter.setLayout(new GridBagLayout());
        
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* Enable Search CheckBox */
        this.mCheckBoxEnableSearch = new JCheckBox("Enable Search");
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, emptyInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mCheckBoxEnableSearch, layoutConstraints);
        
        /* Search Panel */
        this.mPanelSearch = new JPanel();
        this.mPanelSearch.setBorder(new EmptyBorder(this.mDefaultBorderWidth, 0, 0, 0));
        this.mPanelSearch.setLayout(new BoxLayout(this.mPanelSearch, BoxLayout.Y_AXIS));
        this.mPanelSearch.setVisible(this.mCheckBoxEnableSearch.isSelected());
        this.setLayoutConstraints(
            layoutConstraints, 0, 1, 1.0, 0.0, emptyInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mPanelSearch, layoutConstraints);
        
        /* Search Criteria Panel */
        this.mPanelSearchCriteria = new JPanel();
        this.mPanelSearchCriteria.setLayout(new GridBagLayout());
        this.mPanelSearch.add(this.mPanelSearchCriteria);
        
        /* Search Item Name Label */
        this.mLabelSearchItemName = new JLabel("Item Name: ");
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, emptyInsets, GridBagConstraints.NONE);
        this.mPanelSearchCriteria.add(this.mLabelSearchItemName, layoutConstraints);
        
        /* Search Item Name TextBox */
        this.mTextBoxSearchItemName = new JTextField();
        this.mTextBoxSearchItemName.setHorizontalAlignment(JTextField.LEFT);
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 1.0, 0.0, emptyInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelSearchCriteria.add(this.mTextBoxSearchItemName, layoutConstraints);
        
        /* Search Bottom Panel */
        this.mPanelSearchBottom = new JPanel();
        this.mPanelSearchBottom.setLayout(new BoxLayout(this.mPanelSearchBottom, BoxLayout.X_AXIS));
        this.mPanelSearch.add(this.mPanelSearchBottom);
        
        /* Search Button */
        this.mButtonSearch = new JButton("Search");
        this.mPanelSearchBottom.add(Box.createHorizontalGlue());
        this.mPanelSearchBottom.add(this.mButtonSearch);
        
        /* SplitPane */
        this.mSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.mSplitPane.setBorder(new EmptyBorder(this.mDefaultBorderWidth, 0, 0, 0));
        this.setLayoutConstraints(
            layoutConstraints, 0, 2, 1.0, 1.0, emptyInsets, GridBagConstraints.BOTH);
        this.mPanelCenter.add(this.mSplitPane, layoutConstraints);
        
        /* Rental Category TreeView */
        this.mTreeViewRentalCategory = new JTree();
        this.mTreeViewRentalCategory.setRootVisible(false);
        
        /* TreeView ScrollPane */
        this.mScrollPaneTreeView = new JScrollPane(this.mTreeViewRentalCategory);
        this.mSplitPane.setLeftComponent(this.mScrollPaneTreeView);
        
        /* Rental Category TreeModel */
        this.mTreeViewRentalCategoryRootNode = new DefaultMutableTreeNode(null);
        this.mTreeModelRentalCategory = new DefaultTreeModel(this.mTreeViewRentalCategoryRootNode);
        this.mTreeViewRentalCategory.setModel(this.mTreeModelRentalCategory);
        
        /* Items ListView */
        this.mListViewItems = new JList<>();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.mListViewItems);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.mListViewItemsModel = new DefaultListModel<>();
        this.mListViewItems.setModel(this.mListViewItemsModel);
        this.mListViewItems.setCellRenderer(new ItemsListViewRenderer());
        this.mSplitPane.setRightComponent(scrollPane);
        
        /* Bottom Panel */
        this.mBottomPanel = new JPanel();
        this.mBottomPanel.setLayout(new BoxLayout(this.mBottomPanel, BoxLayout.X_AXIS));
        this.mBottomPanel.setBorder(new EmptyBorder(
            0, this.mDefaultBorderWidth,
            this.mDefaultBorderWidth, this.mDefaultBorderWidth));
        this.add(this.mBottomPanel, BorderLayout.SOUTH);
        
        /* Borrow Button */
        this.mButtonBorrowSelectedItem = new JButton("Borrow Selected Item");
        this.mBottomPanel.add(Box.createHorizontalGlue());
        this.mBottomPanel.add(this.mButtonBorrowSelectedItem);
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
        
        this.mCheckBoxEnableSearch.addActionListener(
            e -> this.onCheckBoxEnableSearchClicked(e));
        this.mButtonSearch.addActionListener(
            e -> this.onButtonSearchClicked(e));
        this.mTreeViewRentalCategory.addTreeSelectionListener(
            e -> this.onTreeViewRentalCategorySelectionChanged(e));
    }
    
    @Override
    protected void onPanelSelected()
    {
        this.initializeItems();
    }
    
    private void initializeItems()
    {
        /* Update TreeView */
        this.mTreeViewRentalCategoryRootNode.removeAllChildren();
        this.mMainModel.getRentalCategoryList().forEach(
            rentalCategory -> this.mTreeViewRentalCategoryRootNode.add(
                new DefaultMutableTreeNode(rentalCategory)));
        this.mTreeModelRentalCategory.reload();
        
        /* Update ListView */
        this.mListViewItemsModel.removeAllElements();
    }
    
    private void updateListViewItems(String rentalCategory)
    {
        this.mListViewItemsModel.removeAllElements();
        this.mMainModel.filterRentalObjectByCategory(rentalCategory).forEach(
            rentalObject -> this.mListViewItemsModel.addElement(rentalObject));
    }
    
    private void updateListViewItems(String rentalCategory, String itemName)
    {
        this.mListViewItemsModel.removeAllElements();
        this.mMainModel.filterRentalObjectByNameAndCategory(itemName, rentalCategory)
            .forEach(rentalObject -> this.mListViewItemsModel.addElement(rentalObject));
    }
    
    private void onCheckBoxEnableSearchClicked(ActionEvent e)
    {
        this.mPanelSearch.setVisible(this.mCheckBoxEnableSearch.isSelected());
        
        if (!this.mCheckBoxEnableSearch.isSelected())
            this.initializeItems();
    }
    
    private void onButtonSearchClicked(ActionEvent e)
    {
        /* Retrieve RentalObjects */
        List<RentalObject> filteredRentalObjects =
            this.mMainModel.filterRentalObjectByName(this.mTextBoxSearchItemName.getText());
        Set<String> rentalCategorySet = new HashSet<String>();
        filteredRentalObjects.forEach(
            rentalObject -> rentalCategorySet.add(rentalObject.categoryName()));
        
        /* Update TreeView */
        this.mTreeViewRentalCategoryRootNode.removeAllChildren();
        rentalCategorySet.stream().sorted().forEach(
            rentalCategory -> this.mTreeViewRentalCategoryRootNode.add(
                new DefaultMutableTreeNode(rentalCategory)));
        this.mTreeModelRentalCategory.reload();
        
        /* Update ListView */
        this.mListViewItemsModel.removeAllElements();
    }
    
    private void onTreeViewRentalCategorySelectionChanged(TreeSelectionEvent e)
    {
        DefaultMutableTreeNode selectedNode =
            (DefaultMutableTreeNode)this.mTreeViewRentalCategory.getLastSelectedPathComponent();
        
        if (selectedNode == null)
            return;
        
        String rentalCategory = (String)selectedNode.getUserObject();
        
        if (this.mCheckBoxEnableSearch.isSelected())
            this.updateListViewItems(rentalCategory, this.mTextBoxSearchItemName.getText());
        else
            this.updateListViewItems(rentalCategory);
    }
    
    private GridBagConstraints setLayoutConstraints(
        GridBagConstraints layoutConstraints,
        int gridx, int gridy, double weightx, double weighty, Insets insets, int fill)
    {
        layoutConstraints.gridx = gridx;
        layoutConstraints.gridy = gridy;
        layoutConstraints.weightx = weightx;
        layoutConstraints.weighty = weighty;
        layoutConstraints.insets = insets;
        layoutConstraints.fill = fill;
        
        return layoutConstraints;
    }
    
    public void addBorrowItemListener(ActionListener actionListener)
    {
        this.mButtonBorrowSelectedItem.addActionListener(actionListener);
    }
    
    public RentalObject getSelectedRentalObject()
    {
        return this.mListViewItems.getSelectedValue();
    }
}
