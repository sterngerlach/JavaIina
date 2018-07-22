
/* ReservingItemsPanel.java */

package javaiina;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ReservingItemsPanel extends PanelBase
{
    private static final long serialVersionUID = 5856220651089728844L;
    
    private MainModel mMainModel;
    
    private JList<Reservation> mListViewReservingItems;
    private DefaultListModel<Reservation> mListViewReservingItemsModel;
    
    @Override
    public String getPanelName()
    {
        return "Reserving Items";
    }
    
    public ReservingItemsPanel()
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
        this.setHeaderTitle("Reserving Items");
        
        /* Center Panel */
        this.mPanelCenter.setLayout(new BorderLayout());
        
        /* Reservations ListView */
        this.mListViewReservingItems = new JList<>();
        this.mListViewReservingItemsModel = new DefaultListModel<>();
        this.mListViewReservingItems.setModel(this.mListViewReservingItemsModel);
        this.mListViewReservingItems.setCellRenderer(new ReservationsListViewRenderer());
        this.mPanelCenter.add(this.mListViewReservingItems, BorderLayout.CENTER);
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
    }
    
    @Override
    protected void onPanelSelected()
    {
        this.initializeItems();
    }
    
    @Override
    protected void reloadView()
    {
        this.initializeItems();
    }
    
    private void initializeItems()
    {
        /* Update ListView */
        this.mListViewReservingItemsModel.removeAllElements();
        this.mMainModel.getReservations().forEach(
            reservation -> this.mListViewReservingItemsModel.addElement(reservation));
    }   
}
