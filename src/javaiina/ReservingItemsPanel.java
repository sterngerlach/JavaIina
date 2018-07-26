
/* ReservingItemsPanel.java */

package javaiina;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

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
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this.mListViewReservingItems);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.mListViewReservingItemsModel = new DefaultListModel<>();
        this.mListViewReservingItems.setModel(this.mListViewReservingItemsModel);
        this.mListViewReservingItems.setCellRenderer(new ReservationsListViewRenderer());
        this.mPanelCenter.add(scrollPane, BorderLayout.CENTER);
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
