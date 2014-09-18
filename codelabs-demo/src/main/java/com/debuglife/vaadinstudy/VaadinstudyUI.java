package com.debuglife.vaadinstudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityEvent;
import org.vaadin.hene.popupbutton.PopupButton.PopupVisibilityListener;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.AbstractSelect.NewItemHandler;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Flash;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.CollapseListener;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
@Theme("vaadinstudy")
public class VaadinstudyUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinstudyUI.class)
	public static class Servlet extends VaadinServlet {
	    public Servlet(){
	        System.out.println("Servlet in the VaadinStudyUI");
	    }
	}

	@Override
	protected void init(VaadinRequest request) {
	    initPageWholeLayout();
	    initPage();
	}
	
	// component of page whole layout 
	private VerticalSplitPanel topLevelLayout;
	private Panel headerPanel;
	private HorizontalSplitPanel bottomLayout;
	private Panel accordionPanel;
	private Panel rightPanel;
	private Accordion accordion;
	private VerticalLayout rightLayout;
	
    VerticalLayout vlayout = new VerticalLayout();

	private void initPageWholeLayout(){
	    // 1. top most layout
	    topLevelLayout = new VerticalSplitPanel();
	    topLevelLayout.setSplitPosition(8, Unit.PERCENTAGE);
	    topLevelLayout.setLocked(true);
	    topLevelLayout.setSizeFull();
	    topLevelLayout.setStyleName("v-spliter");
	    
	    // 2. header layout
	    headerPanel = new Panel();
	    headerPanel.setWidth("100%");
	    headerPanel.setHeight("100%");
	    headerPanel.setSizeFull();
	    Label headerLabel = new Label("Vaadin Code Labs");
	    headerLabel.setSizeFull();
	    headerLabel.addStyleName("v-label-header");
	    headerPanel.setContent(headerLabel);
	    
	    // 3. bottom layout
	    bottomLayout = new HorizontalSplitPanel();
	    bottomLayout.setSplitPosition(20, Unit.PERCENTAGE);
	    bottomLayout.setLocked(true);
	    
	    topLevelLayout.setFirstComponent(headerPanel);
	    topLevelLayout.setSecondComponent(bottomLayout);
	    
        accordionPanel = new Panel();
        accordionPanel.setWidth("300px");
        accordionPanel.setHeight("300px");
        accordionPanel.setSizeFull();
        
        rightPanel = new Panel();
        accordion = buildAccordion();
        rightLayout = new VerticalLayout();
        rightLayout.addComponent(vlayout);
        accordionPanel.setContent(accordion);
        rightPanel.setContent(rightLayout);
    
        bottomLayout.setFirstComponent(accordionPanel);
        bottomLayout.setSecondComponent(rightPanel);
        
	    this.setContent(topLevelLayout);
	}
	
	// build the accordion in the left layout
    private Accordion buildAccordion(){
        accordion = new Accordion();
        accordion.setHeight(100.0f, Unit.PERCENTAGE);
        
        Map<String,List<Object>> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        // single tab
        list.add(new Link("hahahahahaha1", null));
        list.add(new Link("hahahahahaha2", null));
        list.add(new Link("hahahahahaha3", null));
        map.put("SingleComponent", list);
        
        // container tab
        List<Object> list1 = new ArrayList<>();
        list1.add(new Link("hahahahahaha1", null));
        list1.add(new Link("hahahahahaha2", null));
        list1.add(new Link("hahahahahaha3", null));
        map.put("Container", list1);
        
        // javascript tab
        List<Object> list2 = new ArrayList<>();
        list1.add(new Link("hahahahahaha1", null));
        list1.add(new Link("hahahahahaha2", null));
        list1.add(new Link("hahahahahaha3", null));
        map.put("JavaScript", list2);
        
        VerticalLayout vlayout = null;
        for(String str : map.keySet()){
            vlayout = new VerticalLayout();
            vlayout.setMargin(new MarginInfo(true,true,false,true));
            for(Object o: map.get(str)){
                vlayout.addComponent((Component)o);
            }
            accordion.addTab(vlayout, str);
        }
        
        return accordion;
    }
	
	private void initPage(){
		// Set the default locale of the UI
		UI.getCurrent().setLocale(new Locale("en"));
		// Set the page title (window or tab caption)
		Page.getCurrent().setTitle("VaadinStudy");
		// Enable layout margins. Affects all four sides of the layout
		vlayout.setMargin(true); 
		vlayout.setSpacing(true);
		
		// Popup Button
        initLabel("PopupButton");
        initPopupButton();
		
	    // JavaScript Component
        initLabel("Java Script Component");
        //initJavaScriptComponent();
        
        // JavaScript Interaciton
        initLabel("JavaScript Interaciton");
        initJavaScript();
        
        // HorizontalSplitPanel
        initLabel("HorizontalSplitPanel");
        initHorizontalSpilitPanel();
        
        // Sub Window
        initLabel("Sub Window");
        initSubWindow();
        
        // Panel
        initLabel("Panel");
        initPanel();
        
        // FormLayout
        initLabel("FormLayout");
        initFormlayout();
        
        // GridLayout
        initLabel("GridLayout");
        initGridLayout();
        
        // SizeContainedComponents
        initLabel("SizeContainedComponents");
        initSizeContainedComponents();
        
        // CC1
        initLabel("CompositComponent1");
        initCompositeComponent1();

        // ProgressBar
        initLabel("ProgressBar");
        initProgressBar();
        
        // Upload 
        initLabel("Upload by myself");
        initUploadTest();
        
        // Common Embedded Object
        initLabel("Embedded");
        initEmbedded();
        
        // BrowserFrame
        initLabel("BrowserFrame");
        initBrowserFrame();
        
        // Flash
        initLabel("Flash");
        initAdobeFlashGraphics();
        
        // Image
        initLabel("Image");
        initImage();
        
        // MenuBar
        initLabel("MenuBar");
        initMenuBar();
        
        // Tree
        initLabel("Tree");
        initTree();
        
        // Notification
        initLabel("Notification");
        initNotification();
        
        // Icon
        initLabel("Icon");
        initIcon();
        
        // Link
        initLabel("Link");
        initLink();
        
        // Upload file
        initLabel("Upload file");
        initUploadFile();
        
        // Slider
        initLabel("Slider");
        initSlider();
        
        // CheckBox
        initLabel("CheckBox");
        initCheckBox();
        
        // OptionGroup
        initLabel("OptionGroup");
        initOptionGroup();
        
        // ListBuilder
        initLabel("ListBuilder");
        initListBuilder();
        
        // ListSelect
        initLabel("ListSelect");
        initListSelect();
        
        // DropDownMenu
        initLabel("DropDownMenu");
        initDropDownMenu();
        
        // ComboBox
        initLabel("ComboBox");
        initComboBox();
        
        // DatePicker
        initLabel("DatePicker");
        initDatePicker();
        
        // PopupDateField
        initLabel("PopupDateField");
        initDateField();
        
        // RichText
        initLabel("RichText");
        initRichText();
        
        // PasswordField
        initLabel("PasswordField");
        PasswordField passwdField = new PasswordField();
        initTextField(passwdField);
        
        // TextField
        initLabel("TextField");
        TextField textField = new TextField();
        initTextField(textField);

        // TextArea
        initLabel("TextArea");
        initMultiRowArea();
        
        // Table
        initLabel("Table");
        initTable();
        
		initSpace();
	}

	private void initPopupButton(){
	    final TextArea textArea = new TextArea("Multi-line TextField");
	    textArea.setImmediate(true);
	    textArea.addValueChangeListener(new Property.ValueChangeListener() {
	        @Override
	        public void valueChange(Property.ValueChangeEvent event) {
	            Notification.show("" + event.getProperty().getValue());
	        }
	    });
	    textArea.setRows(10);
	    
	    PopupButton popupButton = new PopupButton("Action");
        popupButton.addPopupVisibilityListener(new PopupVisibilityListener() {
            @Override
            public void popupVisibilityChange(PopupVisibilityEvent event) {
                if (event.isPopupVisible()) {
                    textArea.focus();
                }
            }
        });
        popupButton.addClickListener(new ClickListener(){
            @Override
            public void buttonClick(ClickEvent event) {
                textArea.focus();
            }
        });
        popupButton.setContent(textArea);
	    
	    vlayout.addComponent(popupButton);
	    initSpace();
	}
	
    private void initTable() {
        // Access the HTTP service parameter
        File baseDir =  VaadinService.getCurrent().getBaseDirectory();
        HorizontalLayout hlayout = new HorizontalLayout();
	    final TextField tf = new TextField();
	    final Table table = new Table();
	    
		Label label1 = new Label(baseDir.getAbsolutePath());
		vlayout.addComponent(label1);
		
		Label label = new Label("RSS View");
		vlayout.addComponent(label);
		
		hlayout.setMargin(true);
		hlayout.setMargin(new MarginInfo(true,true,true,false));
		hlayout.setSizeFull();
		
		tf.setSizeFull();
		tf.setDescription("Please input valid url");
		Button button = new Button("Retrieve Content");
		button.setDescription("Please click it");
		hlayout.addComponent(tf);
		hlayout.setExpandRatio(tf, 1);
		hlayout.addComponent(button);
		hlayout.setExpandRatio(button, 3);
		
		vlayout.addComponent(hlayout);	// Expand to fill
		vlayout.setExpandRatio(hlayout, 1);
		
		//table.setSizeFull();
		table.setWidth("50%");
		table.addContainerProperty("Title", String.class, null);
		table.addContainerProperty("Link", String.class, null);
		table.addContainerProperty("PubDate", String.class, null);
		vlayout.addComponent(table);
		vlayout.setExpandRatio(table, 1);

		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String text = tf.getValue();
				List<Map<String,String>> list = ParseXml.parseXmlString(text);
				System.out.println(list);
				Map<String,String> map = null;
				for(int i=0;i<list.size();i++){
					map = list.get(i);
					table.addItem(new Object[]{map.get("title"),map.get("link"),map.get("pubDate")}, new Integer(i));
				}
			}
		});
		
		initSpace();
    }
	
	/**
	 * Add a label to indicate which component it is.
	 * @param typeName component type name
	 */
	private void initLabel(String typeName){
		Label label = new Label(typeName);
		label.addStyleName("v-label-mystyle");
		vlayout.addComponent(label);
	}
	
	/*
	 * Add some blank space in the bottom of page
	 */
	private void initSpace(){
		for(int i=0;i<5;i++){
			Label l = new Label();
			l.setHeight(5f, Unit.PIXELS);
			vlayout.addComponent(l);
		}
	}
	
	/**
	 * Add a textArea
	 */
	private void initMultiRowArea(){
		final String initialText = "The quick brown fox jumps over the lazy dog";
		TextArea textArea = new TextArea(null,initialText);
		textArea.setRows(10);
		textArea.setImmediate(true);
		textArea.setWidth("50%");
		//textArea.setSizeFull();
		vlayout.addComponent(textArea);
		vlayout.setExpandRatio(textArea, 1);
		
		textArea.addValueChangeListener(new ValueChangeListener(){ 
			@Override
			public void valueChange(ValueChangeEvent event) {
				 final String valueString = String.valueOf(event.getProperty()
	                        .getValue());
	                Notification.show("Value changed:", valueString,
	                        Type.TRAY_NOTIFICATION);
			}
		});
		
		vlayout.addComponent(textArea);
		initSpace();
	}
	
	/**
	 * Add a textField component
	 * @param atf
	 */
	private void initTextField(final AbstractTextField atf){
		atf.setImmediate(true);
		atf.setInputPrompt("Write something");
		atf.setMaxLength(10);
		atf.setWidth("50%");
		updateCaption(atf,0);

		atf.addTextChangeListener(new TextChangeListener() {
            @Override
            public void textChange(final TextChangeEvent event) {
            	updateCaption(atf,event.getText().length());
            }
        });
	 
		atf.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.HUMANIZED_MESSAGE);
            }
	    });
		
		vlayout.addComponent(atf);
		initSpace();
	}
	
	/**
     * Updates the textField caption
     */
    private void updateCaption(final AbstractTextField atf,final int textLength) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(textLength));
        if (atf.getMaxLength() >= 0) {
            builder.append("/").append(atf.getMaxLength());
        }
        builder.append(" characters");
        atf.setCaption(builder.toString());
    }
    
    /**
     * Add a richText component
     */
    private void initRichText(){
    	 final String initialText = "The quick brown fox jumps over the lazy dog.";
    	 RichTextArea richTextArea = new RichTextArea(null, initialText);
         richTextArea.setImmediate(true);
         //richTextArea.setSizeFull();
         richTextArea.setWidth("50%");

         richTextArea.addValueChangeListener(new ValueChangeListener() {
             @Override
             public void valueChange(final ValueChangeEvent event) {
                 final String valueString = String.valueOf(event.getProperty()
                         .getValue());
                 Notification.show("Value changed:", valueString,
                         Type.TRAY_NOTIFICATION);
             }
         });
         
         vlayout.addComponent(richTextArea);
         initSpace();
    }
    
    /*
     * Add a date field 
     */
    private void initDateField(){
    	PopupDateField popupDateField = new PopupDateField();
    	popupDateField.setValue(new Date());
    	popupDateField.setImmediate(true);
    	popupDateField.setTimeZone(TimeZone.getTimeZone("UTC"));
    	popupDateField.setLocale(Locale.US);
    	popupDateField.setResolution(Resolution.MINUTE);
  
    	popupDateField.addValueChangeListener(new ValueChangeListener() {
             @Override
             public void valueChange(final ValueChangeEvent event) {
                 final String valueString = String.valueOf(event.getProperty()
                         .getValue());
                 Notification.show("Value changed:", valueString,
                         Type.TRAY_NOTIFICATION);
             }
         });
    	
    	vlayout.addComponent(popupDateField);
    	initSpace();
    }
    
    /*
     * Add a date picker component
     */
    private void initDatePicker(){
    	InlineDateField inlineDateField = new InlineDateField();
    	inlineDateField.setValue(new Date());
    	inlineDateField.setImmediate(true);
    	inlineDateField.setTimeZone(TimeZone.getTimeZone("UTC"));
    	inlineDateField.setLocale(Locale.US);
    	inlineDateField.setResolution(Resolution.MINUTE);
 
    	inlineDateField.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
            }
        });
    	
    	vlayout.addComponent(inlineDateField);
    	initSpace();
    	
    }
    
    /*
     * Add a ComboBox component
     */
    @SuppressWarnings("unchecked")
    private void initComboBox(){
    	 // Creates a new combobox using an existing container
    	final ComboBox comboBox = new ComboBox("Select your country",
                ExampleUtil.getISO3166Container());
    	comboBox.setInputPrompt("No country selected");

        // Sets the combobox to show a certain property as the item caption
    	comboBox.setItemCaptionPropertyId(ExampleUtil.iso3166_PROPERTY_NAME);
    	comboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);

        // Sets the icon to use with the items
    	comboBox.setItemIconPropertyId(ExampleUtil.iso3166_PROPERTY_FLAG);

        // Set a reasonable width
    	comboBox.setWidth(350.0f, Unit.PIXELS);

        // Set the appropriate filtering mode for this example
    	comboBox.setFilteringMode(FilteringMode.CONTAINS);
    	comboBox.setImmediate(true);

        // Disallow null selections
    	comboBox.setNullSelectionAllowed(false);

        // Check if the caption for new item already exists in the list of item
        // captions before approving it as a new item.
    	comboBox.setNewItemHandler(new NewItemHandler() {
           
			@Override
            public void addNewItem(final String newItemCaption) {
                boolean newItem = true;
                for (final Object itemId : comboBox.getItemIds()) {
                    if (newItemCaption.equalsIgnoreCase(comboBox
                            .getItemCaption(itemId))) {
                        newItem = false;
                        break;
                    }
                }
                if (newItem) {
                    // Adds new option
                    if (comboBox.addItem(newItemCaption) != null) {
                        final Item item = comboBox.getItem(newItemCaption);
                        item.getItemProperty(ExampleUtil.iso3166_PROPERTY_NAME)
                                .setValue(newItemCaption);
                        comboBox.setValue(newItemCaption);
                    }
                }
            }
        });

    	comboBox.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
            }
        });
    	
    	vlayout.addComponent(comboBox);
    	initSpace();
    }
    
    /*
     * Add a DropDownMenu component
     */
    private void initDropDownMenu(){
    	NativeSelect nativeSelect = new NativeSelect("Selecct an option");
    	for(int i=0;i<6;i++){
    		nativeSelect.addItem(i);
    		nativeSelect.setItemCaption(i, "Option" + i);
    	}
    	
    	nativeSelect.setNullSelectionAllowed(false);
    	nativeSelect.setValue(2);
    	nativeSelect.setImmediate(true);
    	nativeSelect.setWidth("50%");
    	
    	nativeSelect.addValueChangeListener(new ValueChangeListener(){
    		@Override
    		 public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
            }
    	});
    	
    	vlayout.addComponent(nativeSelect);
    	initSpace();
    }
    
    /**
     * Add a List Select component
     */
    private void initListSelect(){
    	ListSelect listSelect = new ListSelect("Select an option");
         for (int i = 0; i < 6; i++) {
        	 listSelect.addItem(i);
        	 listSelect.setItemCaption(i, "Option " + i);
         }

         listSelect.setRows(6); // perfect length in out case
         listSelect.setNullSelectionAllowed(false); // user can not 'unselect'
         listSelect.select(2); // select this by default
         listSelect.setImmediate(true); // send the change to the server at once
         listSelect.setWidth("50%");
  
         listSelect.addValueChangeListener(new ValueChangeListener() {
             @Override
             public void valueChange(final ValueChangeEvent event) {
                 final String valueString = String.valueOf(event.getProperty()
                         .getValue());
                 Notification.show("Value changed:", valueString,
                         Type.TRAY_NOTIFICATION);
             }
         });
         
         vlayout.addComponent(listSelect);
         initSpace();
    }
    
    /**
     * Add a List Builder
     */
    private void initListBuilder(){
    	  TwinColSelect twinColSelect = new TwinColSelect();
	      for (int i = 0; i < 6; i++) {
	    	  twinColSelect.addItem(i);
	    	  twinColSelect.setItemCaption(i, "Option " + i);
	      }
	      twinColSelect.setRows(6);
	      twinColSelect.setNullSelectionAllowed(true);
	      twinColSelect.setMultiSelect(true);
	      twinColSelect.setImmediate(true);
	      twinColSelect.setLeftColumnCaption("Available options");
	      twinColSelect.setRightColumnCaption("Selected options");
	      twinColSelect.setWidth("50%");
	
	      twinColSelect.addValueChangeListener(new ValueChangeListener() {
	          @Override
	          public void valueChange(final ValueChangeEvent event) {
	              final String valueString = String.valueOf(event.getProperty()
	                      .getValue());
	              Notification.show("Value changed:", valueString,
	                      Type.TRAY_NOTIFICATION);
	          }
	      });
	      
	      vlayout.addComponent(twinColSelect);
	      initSpace();
    }
    
    /**
     * Add a Option group
     */
    private void initOptionGroup(){
    	final OptionGroup optionGroup = new OptionGroup("Select an option");
         for (int i = 0; i < 5; i++) {
        	 optionGroup.addItem(i);
        	 optionGroup.setItemCaption(i, "Option " + i);
         }
         optionGroup.select(2);
         optionGroup.setNullSelectionAllowed(false);
         optionGroup.setHtmlContentAllowed(true);
         optionGroup.setImmediate(true);
         optionGroup.setWidth("50%");

         final CheckBox disableOptionsCheckBox = new CheckBox(
                 "Some options disabled");
         disableOptionsCheckBox.setImmediate(true);
         disableOptionsCheckBox
                 .addValueChangeListener(new ValueChangeListener() {
                     @Override
                     public void valueChange(final ValueChangeEvent event) {
                         final boolean value = (Boolean) event.getProperty()
                                 .getValue();
                         optionGroup.setItemEnabled(1, !value);
                         optionGroup.setItemEnabled(3, !value);
                     }
                 });

         final CheckBox htmlCaptionsCheckBox = new CheckBox(
                 "Captions with HTML content");
         htmlCaptionsCheckBox.setImmediate(true);
         htmlCaptionsCheckBox.addValueChangeListener(new ValueChangeListener() {
             @Override
             public void valueChange(final ValueChangeEvent event) {
                 for (int i = 0; i < 5; i++) {
                	 optionGroup.setItemCaption(i, "Option " + i);
                 }

                 if ((Boolean) event.getProperty().getValue()) {
                	 optionGroup.setItemCaption(1, "<b>" + optionGroup.getItemCaption(1)
                             + "</b>");
                	 optionGroup.setItemCaption(2, "<u>" + optionGroup.getItemCaption(2)
                             + "</u>");
                 }
             }
         });
  
         optionGroup.addValueChangeListener(new ValueChangeListener() {
             @Override
             public void valueChange(final ValueChangeEvent event) {
                 final String valueString = String.valueOf(event.getProperty()
                         .getValue());
                 Notification.show("Value changed:", valueString,
                         Type.TRAY_NOTIFICATION);
             }
         });
         
         vlayout.addComponent(optionGroup);
         initSpace();
    }
    
    /*
     * Add a CheckBox
     */
    private void initCheckBox(){
    	CheckBox checkBox = new CheckBox("CheckBox",true);
    	//checkBox.setEnabled(false);
    	
    	checkBox.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
            }
        });
    	
    	vlayout.addComponent(checkBox);
    	initSpace();
    }

    /**
     * Add a simple Slider
     */
    private void initSlider(){
    	Slider slider = new Slider();
    	slider.setImmediate(true);
    	slider.setMin(0.0);
    	slider.setMax(100.0);
    	slider.setValue(50.0);
    	slider.setWidth("50%");

    	slider.addValueChangeListener(new ValueChangeListener() {
             @Override
             public void valueChange(final ValueChangeEvent event) {
                 final String valueString = String.valueOf(event.getProperty()
                         .getValue());
                 Notification.show("Value changed:", valueString,
                         Type.TRAY_NOTIFICATION);
             }
         });
    	
    	vlayout.addComponent(slider);
    	initSpace();
    }
    
    /**
     * Add a complex slider
     */
    private void initComplexSlider(){
    	
    }

	private void initUploadFile() {
		class LineBreakCounter implements Receiver {
			private int counter;
			private int total;
			private boolean sleep;

			/**
			 * return an OutputStream that simply counts lineends
			 */
			@Override
			public OutputStream receiveUpload(final String filename,
					final String MIMEType) {
				counter = 0;
				total = 0;
				return new OutputStream() {
					private static final int searchedByte = '\n';

					@Override
					public void write(final int b) throws IOException {
						total++;
						if (b == searchedByte) {
							counter++;
						}
						if (sleep && total % 1000 == 0) {
							try {
								Thread.sleep(100);
							} catch (final InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
			}

			public int getLineBreakCount() {
				return counter;
			}

			public void setSlow(final boolean value) {
				sleep = value;
			}

		}
		
		@StyleSheet("uploadexample.css")
		class UploadInfoWindow extends Window implements
				Upload.StartedListener, Upload.ProgressListener,
				Upload.FailedListener, Upload.SucceededListener,
				Upload.FinishedListener {
			private final Label state = new Label();
			private final Label result = new Label();
			private final Label fileName = new Label();
			private final Label textualProgress = new Label();

			//private final ProgressIndicator pi = new ProgressIndicator();
			private final ProgressBar pi = new ProgressBar();
			private final Button cancelButton;
			private final LineBreakCounter counter;

			public UploadInfoWindow(final Upload upload,
					final LineBreakCounter lineBreakCounter) {
				super("Status");
				this.counter = lineBreakCounter;

				addStyleName("upload-info");

				setResizable(false);
				setDraggable(true);

				final FormLayout l = new FormLayout();
				setContent(l);
				l.setMargin(true);

				final HorizontalLayout stateLayout = new HorizontalLayout();
				stateLayout.setSpacing(true);
				stateLayout.addComponent(state);

				cancelButton = new Button("Cancel");
				cancelButton.addClickListener(new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						upload.interruptUpload();
					}
				});
				cancelButton.setVisible(false);
				cancelButton.setStyleName("small");
				stateLayout.addComponent(cancelButton);

				stateLayout.setCaption("Current state");
				state.setValue("Idle");
				l.addComponent(stateLayout);

				fileName.setCaption("File name");
				l.addComponent(fileName);

				result.setCaption("Line breaks counted");
				l.addComponent(result);

				pi.setCaption("Progress");
				pi.setVisible(false);
				l.addComponent(pi);

				textualProgress.setVisible(false);
				l.addComponent(textualProgress);

				upload.addStartedListener(this);
				upload.addProgressListener(this);
				upload.addFailedListener(this);
				upload.addSucceededListener(this);
				upload.addFinishedListener(this);

			}

			@Override
			public void uploadFinished(final FinishedEvent event) {
				state.setValue("Idle");
				pi.setVisible(false);
				textualProgress.setVisible(false);
				cancelButton.setVisible(false);
			}

			@Override
			public void uploadStarted(final StartedEvent event) {
				// this method gets called immediatedly after upload is started
				pi.setValue(0f);
				pi.setVisible(true);
				setPollInterval(500);
				//pi.setPollingInterval(500); // hit server frequantly to get
				textualProgress.setVisible(true);
				// updates to client
				state.setValue("Uploading");
				fileName.setValue(event.getFilename());

				cancelButton.setVisible(true);
			}

			@Override
			public void updateProgress(final long readBytes,
					final long contentLength) {
				// this method gets called several times during the update
				pi.setValue(new Float(readBytes / (float) contentLength));
				textualProgress.setValue("Processed " + readBytes
						+ " bytes of " + contentLength);
				result.setValue(counter.getLineBreakCount() + " (counting...)");
			}

			@Override
			public void uploadSucceeded(final SucceededEvent event) {
				result.setValue(counter.getLineBreakCount() + " (total)");
			}

			@Override
			public void uploadFailed(final FailedEvent event) {
				result.setValue(counter.getLineBreakCount()
						+ " (counting interrupted at "
						+ Math.round(100 * pi.getValue()) + "%)");
			}
		}

		LineBreakCounter lineBreakCounter = new LineBreakCounter();
		lineBreakCounter.setSlow(true);

		Upload upload = new Upload(null, lineBreakCounter);
		upload.setImmediate(false);
		upload.setButtonCaption("Upload File");
		

		final UploadInfoWindow uploadInfoWindow = new UploadInfoWindow(upload, lineBreakCounter);
		upload.addStartedListener(new StartedListener() {
			@Override
			public void uploadStarted(final StartedEvent event) {
				if (uploadInfoWindow.getParent() == null) {
					UI.getCurrent().addWindow(uploadInfoWindow);
				}
				uploadInfoWindow.setVisible(true);
				uploadInfoWindow.setClosable(false);
			}
		});

		upload.addFinishedListener(new Upload.FinishedListener() {
			@Override
			public void uploadFinished(final FinishedEvent event) {
				uploadInfoWindow.setClosable(true);
			}
		});
		
		this.addWindow(uploadInfoWindow);
		uploadInfoWindow.setVisible(false);
		vlayout.addComponent(upload);
	
		initSpace();
	}

	/*
	 * Add a link
	 */
	private void initLink(){
		final String CAPTION = "Open Google";
		final String TOOLTIP = "http://www.google.com";
		final ThemeResource ICON = new ThemeResource("../sampler/icons/icon_world.gif");
		
		 // Link w/ text and tooltip
        Link l = new Link(CAPTION,
                new ExternalResource("http://www.google.com"));
        l.setDescription(TOOLTIP);
        vlayout.addComponent(l);

        // Link w/ text, icon and tooltip
        l = new Link(CAPTION, new ExternalResource("http://www.google.com"));
        l.setDescription(TOOLTIP);
        l.setIcon(ICON);
        vlayout.addComponent(l);

        // Link w/ icon and tooltip
        l = new Link();
        l.setResource(new ExternalResource("http://www.google.com"));
        l.setDescription(TOOLTIP);
        l.setIcon(ICON);
        vlayout.addComponent(l);
        
        initSpace();
	}
	
	/**
	 * Add Some Icons
	 */
	private void initIcon(){
		/* Button w/ icon */
        Button button = new Button("Save");
        button.setIcon(new ThemeResource("../sampler/icons/action_save.gif"));
        vlayout.addComponent(button);

        /* Label */;
        Label l = new Label("Icons are very handy");
        l.setCaption("Comment");
        l.setIcon(new ThemeResource("../sampler/icons/comment_yellow.gif"));
        vlayout.addComponent(l);

        
        Link lnk = new Link("http://vaadin.com", new ExternalResource(
                "http://www.vaadin.com"));
        lnk.setIcon(new ThemeResource("../sampler/icons/icon_world.gif"));
        /* Panel w/ links */
        Panel p = new Panel("Handy links",lnk);
        p.setWidth("50%");
        p.setIcon(new ThemeResource("../sampler/icons/icon_info.gif"));
        vlayout.addComponent(p);
        initSpace();


	}
	
	/**
	 * Add a notification
	 */
	private void initNotification(){
		final Notification notify = new Notification("Notification messsage","Save Failded for dummy",Notification.Type.ERROR_MESSAGE);
		notify.setPosition(Position.MIDDLE_CENTER);
		
		Button showButton = new Button("Show notification", 
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						notify.show(Page.getCurrent());
					}
				});
		
		vlayout.addComponent(showButton);
		initSpace();
	}
	
	/**
	 * Add a tree
	 */
	private void initTree(){
		// dummy data source
		final Object[][] planets = new Object[][]{
			new Object[]{"Mercury"},
			new Object[]{"Venus"},
			new Object[]{"Earth","The Moon"},
			new Object[]{"Mars","Phobos","Deimos"},
			new Object[]{"Jupiter","Io","Europa","Ganymedes","Callisto"}
		};
		
		final Tree tree = new Tree("The planets and The major moons");
		
		for(int i=0;i<planets.length;i++){
			String planet = (String)(planets[i][0]);
			tree.addItem(planet);
			
			if(planets[i].length == 1)
				// The planet has no moons so make it a leaf
				tree.setChildrenAllowed(planet, false);
			else{
				//Add children(moons) under the planet
				for(int j=1;j<planets[i].length;j++){ // j=0 is trap, note!!!
					String moon = (String)planets[i][j];
					tree.addItem(moon);
					tree.setParent(moon, planet);
					tree.setChildrenAllowed(moon, false);
				}
				
				// Expand the subtree
				tree.expandItemsRecursively(planet);
			}
		}
		
		tree.addValueChangeListener(new ValueChangeListener(){
			@Override
			public void valueChange(ValueChangeEvent event) {
				String str = (String)tree.getValue();
				Notification.show("msg is: ", str, Notification.Type.TRAY_NOTIFICATION);
			}
			
		});
		
		tree.addCollapseListener(new CollapseListener(){
			@Override
			public void nodeCollapse(CollapseEvent event) {
				Notification.show("msg is: ", (String)(tree.getValue()), Notification.Type.TRAY_NOTIFICATION);
			}
			
		});
		
		vlayout.addComponent(tree);
		initSpace();
	}
	
	/**
	 * Add a MenuBar
	 */
	private void initMenuBar(){
		// A feedback component
		final Label selection = new Label("-");
		vlayout.addComponent(selection);
		
		// Define a common menu command for all the menu item
		MenuBar.Command mycommand = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				selection.setValue(selectedItem.getText());
			}
		};
		
		MenuBar menubar = new MenuBar();
		MenuItem drinks = menubar.addItem("Beverage", null, null); //the sub-level menu cannot appear when customized command given.
		MenuItem hots = drinks.addItem("hot", null, null);
		hots.addItem("Tea",null,null);
		hots.addItem("Coffee", null,null);
		
		MenuItem meats = menubar.addItem("ProtectedAnimals", null, mycommand);
		meats.addItem("Monkey", new ThemeResource("../sampler/icons/vaadin-logo.png"), mycommand);
		meats.addItem("Dragon", new ThemeResource("../sampler/icons/vaadin-logo.png"), mycommand);
		
		vlayout.addComponent(menubar);
		
		initSpace();
	}
	
	/**
	 * Add a image
	 */
	private void initImage(){
		Image image = new Image("Yes, logo:", new ClassResource("vaadin-logo.png"));
		vlayout.addComponent(image);
		
		// Serve the image from the theme
		Resource res = new ThemeResource("../sampler/icons/vaadin-logo.png");
		Image image1 = new Image(null,res);
		vlayout.addComponent(image1);
		initSpace();
	}
	
	/**
	 * Add a Flash
	 */
	private void initAdobeFlashGraphics(){
		Flash flash = new Flash("Flash Testing demo", new ThemeResource("../sampler/swf/flash.swf"));
		vlayout.addComponent(flash);
		
		initSpace();
	}
	
	/**
	 * Add a BrowserFrame
	 */
	private void initBrowserFrame(){
		BrowserFrame bf = new BrowserFrame("Browser", new ExternalResource("http://demo.vaadin.com/sampler/"));
		bf.setWidth("600px");
		bf.setHeight("200px");
		//vlayout.addComponent(bf);
		
		initSpace();
	}
	
	/**
	 * Add a embedded object
	 */
	private void initEmbedded(){
		List<Resource> resList =  new ArrayList<Resource>();
		Resource res = new ThemeResource("../sampler/swf/flash.swf");
		Resource res1 = new ThemeResource("../sampler/svg/iPodBlack.svg"); //the space in the path is not allowed!!
		Resource res2 = new ThemeResource("../sampler/svg/iPodWhite.svg"); //the space in the path is not allowed!!
		resList.add(res);
		resList.add(res1);
		resList.add(res2);
		
		HorizontalLayout hlayout1 = new HorizontalLayout();
		hlayout1.setMargin(true);
		Embedded object = null;
		for(int i=0; i<resList.size();i++){
			object = new Embedded("My Common Embedded object" + i, resList.get(i));
			hlayout1.addComponent(object);
		}
		
		vlayout.addComponent(hlayout1);
		initSpace();
	}
	
	/**
	 * Add a upload by myself
	 */
	private void initUploadTest(){
		// used to show the uploaded resource
		final Embedded image = new Embedded("Uploaded Image");
		image.setWidth("600px");
		image.setHeight("400px");
		image.setVisible(false);
		
		class ImageUploader implements Upload.Receiver, Upload.SucceededListener,
									   Upload.FailedListener, Upload.StartedListener{
			public File file = null;
			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				// Create upload stream
				FileOutputStream fos = null; // Stream to write to
				try{
					// Open the file for writing
					file = new File("c:\\test\\upload\\" + filename);
					fos = new FileOutputStream(file);
				} catch(final FileNotFoundException e){
					return null;
				} 
				return fos;
			}
			
			@Override
			public void uploadSucceeded(SucceededEvent event) {
				// Show the uploaded file in the image viewer
				image.setVisible(true);
				image.setSource(new FileResource(file));
				
				//Notification.show(null, "Upload succeed",Notification.Type.TRAY_NOTIFICATION);
			}

			@Override
			public void uploadFailed(FailedEvent event) {
				Notification.show(null, "Upload failed", Notification.Type.ERROR_MESSAGE);
			}

			@Override
			public void uploadStarted(StartedEvent event) {
				Notification.show(null, "Upload started", Notification.Type.TRAY_NOTIFICATION);
			}
			
		}
		
		ImageUploader imgUploader = new ImageUploader();
		Upload upload = new Upload("Upload Image Here",imgUploader);
		upload.setButtonCaption("Start Upload");
		upload.addSucceededListener(imgUploader);
		vlayout.addComponent(upload);
		vlayout.addComponent(image);
		
		initSpace();
	}
	
	/**
	 * Add a ProgressBar
	 */
	private void initProgressBar(){
		final ProgressBar pb = new ProgressBar(0.0f);
		//pb.setSizeFull();
		pb.setWidth("50%");
		vlayout.addComponent(pb);
		vlayout.addComponent(new Button("Increase", new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				float currentValue = pb.getValue();
				if(currentValue < 1.0f){
					currentValue += 0.1f;
					pb.setValue(currentValue);
				}
			}
		}));
		
		vlayout.addComponent(new Button("Decrease", new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				float currentValue = pb.getValue();
				if(currentValue > 0.0f){
					currentValue -= 0.1f;
					pb.setValue(currentValue);
				}
			}
		}));
		
		initSpace();
	}
	
	/**
	 * Add Composite Component
	 */
	private void initCompositeComponent1(){
		CompositeComponent1 cc1 = new CompositeComponent1("hello");
		
		vlayout.addComponent(cc1);
		
		initSpace();
	}
	
	
/**-----------------------------------------------------------------**/
/** Below is container instead of single component
/**
/**
/**------------------------------------------------------------------**/
	
	/**
	 * Test the features of layout
	 */
	private void initSizeContainedComponents(){
		subInitSizeContainedComponents(Sizeable.SIZE_UNDEFINED);
		subInitSizeContainedComponents(600.0f);
		
		// 1:1:1
		subExpandRatios();
		// 1:2:3
		subExpandRatios1();
		// 1:2:3
		subExpandRatios2();
		
		initSpace();
	}
	
	private void subInitSizeContainedComponents(float sizeType){
		HorizontalLayout fittingLayout = new HorizontalLayout();
		fittingLayout.setSizeFull();
		fittingLayout.addStyleName("v-horizontal-layout");
		fittingLayout.setWidth(sizeType, Sizeable.Unit.PIXELS);
		fittingLayout.addComponent(new Button("Small"));
		fittingLayout.addComponent(new Button("Medium-sized"));
		fittingLayout.addComponent(new Button("Quite a big component"));
		vlayout.addComponent(fittingLayout); 
	}
	
	// expand ratios
	private void subExpandRatios(){
		HorizontalLayout hlayout1 = new HorizontalLayout();
		hlayout1.setWidth("600px");
		hlayout1.addStyleName("v-horizontal-layout");
		
		// Create three equally expanding components.
		String[] captionArray = {"Small", "Medium-sized", "Quite a big component"};
		for(int i=0; i<captionArray.length; i++){
			Button button = new Button(captionArray[i]);
			button.setWidth("100%");
			hlayout1.addComponent(button);
			
			// Have uniform 1:1:1 expand ratio.
			hlayout1.setExpandRatio(button, 1.0f);
		}
		
		vlayout.addComponent(hlayout1);
	}
	
	// expand ratios
	private void subExpandRatios1(){
		HorizontalLayout hlayout1 = new HorizontalLayout();
		hlayout1.setWidth("600px");
		hlayout1.addStyleName("v-horizontal-layout");
		
		// Create three equally expanding components.
		String[] captionArray = {"Small", "Medium-sized", "Quite a big component"};
		for(int i=1; i<=captionArray.length; i++){
			Button button = new Button(captionArray[i-1]);
			button.setWidth("100%");
			hlayout1.addComponent(button);
			
			// Have uniform 1:2:3 expand ratio.
			hlayout1.setExpandRatio(button, i*1.0f);
		}
		
		vlayout.addComponent(hlayout1);
	}
	
	// expand ratios
	private void subExpandRatios2(){
		HorizontalLayout hlayout1 = new HorizontalLayout();
		hlayout1.setWidth("600px");
		hlayout1.addStyleName("v-horizontal-layout");
		
		// Create three equally expanding components.
		String[] captionArray = {"Small", "Medium-sized", "Quite a big component"};
		for(int i=1; i<=captionArray.length; i++){
			Button button = new Button(captionArray[i-1]);
			button.setWidth("50%");
			hlayout1.addComponent(button);
			
			// Have uniform 1:2:3 expand ratio.
			hlayout1.setExpandRatio(button, i*1.0f);
		}
		
		vlayout.addComponent(hlayout1);
	}
	
	/** 
	 * Add a grid layout
	 */
	private void initGridLayout(){
		GridLayout glayout = new GridLayout(4,4);
		//glayout.setMargin(true);
		glayout.setStyleName("v-grid-layout");
		
		// Fill out the first row using the cursor.
		glayout.addComponent(new Button("R/C 1"));
		for(int i=0;i<3;i++){
			glayout.addComponent(new Button("Col " + (glayout.getCursorX() + 1)));
		}
		
		for(int j=1;j<4;j++){
			glayout.addComponent(new Button("Row " + j), 0, j);
		} 
		
		// Add some components of various shapes.
		glayout.addComponent(new Button("3x1 button"),1,1,3,1);
		glayout.addComponent(new Label("1x2 cell"), 1, 2, 1, 3);
		InlineDateField date = new InlineDateField("A 2x2 date field");
		date.setResolution(Resolution.DAY);
		glayout.addComponent(date, 2, 2, 3, 3);
	  
		vlayout.addComponent(glayout);
		initSpace();
	}
	
	/**
	 * Add a Form Layout 
	 */
	private void initFormlayout(){
		FormLayout formLayout = new FormLayout();
		formLayout.setSizeUndefined();
		
		TextField tf = new TextField("A Field");
		formLayout.addComponent(tf);
		
		tf.setRequired(true);
		tf.setRequiredError("The Field may not be empty.");
		
		TextField tf2 = new TextField("Another Field");
		formLayout.addComponent(tf2);
		
		tf2.setComponentError(new UserError("This is the error indicator of a field"));
		
		vlayout.addComponent(formLayout);
		initSpace();
	}
	
	/**
	 * Add a panel
	 */
	private void initPanel(){
		// normal panel
		Panel panel = new Panel("Astronomy Panel");
		panel.setStyleName("mypanelexample");
		panel.setSizeUndefined(); //shrink to fit content
		vlayout.addComponent(panel);
		
		FormLayout content = new FormLayout();
		content.setStyleName("mypanelexample");
		content.addComponent(new TextField("Participant"));
		content.addComponent(new TextField("Organization"));
		content.setSizeUndefined();
		content.setMargin(true);
		panel.setContent(content);
		
		initSpace();
		
		// panel with scroll bar 
		Image image = new Image(null, new ThemeResource("../vaadinstudy/images/Tulips.jpg"));
		image.setSizeUndefined(); // shrink the content size
		
		Panel panel1 = new Panel("Scroll Panel");
		panel1.setWidth("600px");
		panel1.setHeight("300px");
		panel1.setContent(image); //if image is replaced with panel1, which will lead to memory leak.
		
		vlayout.addComponent(panel1);
		initSpace();
	}
	
	/*
	 * Add a sub windows
	 */
	private void initSubWindow(){
		// method 1 
		Window window = new Window("Sub Windows");
		VerticalLayout vl = new VerticalLayout();
		window.setContent(vl);
		
		vl.addComponent(new Label("Meatball sub"));
		vl.addComponent(new Button("all right"));
		
		window.center();
		//addWindow(window);
		
		// method 2
		class MySub extends Window{
			public MySub(){
				super("Subs on Sale");
				center();
				
				// Some basic content for the window
				VerticalLayout content = new VerticalLayout();
				content.addComponent(new Label("Just say it's OK!"));
				content.setMargin(true);
				setContent(content);
				
				final Object obj = this;
				Button ok = new Button("OK");
				ok.addClickListener(new ClickListener(){
					@Override
					public void buttonClick(ClickEvent event) {
						((Window)obj).close(); // this.close() is not allowed!!
					}
				});
				
				content.addComponent(ok);
			}
			
			@Override
			public void setClosable(boolean bool){
				super.setClosable(bool);
			}
		}
		
		Button open = new Button("Open Sub-Window");
		open.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				MySub sub = new MySub();
				sub.setHeight("200px");
				sub.setWidth("400px");
				
				sub.setPositionX(200);
				sub.setPositionY(50);
				
				sub.setClosable(false);
				//UI.getCurrent().addWindow(sub); //same as below
				addWindow(sub);
			}
		});
		
		vlayout.addComponent(open);
		
		Button open1 = new Button("Open Sub-Window with scrolling bar");
		open1.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				Image image = new Image(null, new ThemeResource("../vaadinstudy/images/Tulips.jpg"));
				image.setSizeUndefined();
					
				MySub sub = new MySub();
				sub.setHeight("200px");
				sub.setWidth("400px");
				
				sub.setPositionX(200);
				sub.setPositionY(50);
				
				sub.setClosable(true);
				sub.setContent(image);
				//UI.getCurrent().addWindow(sub); //same as below
				addWindow(sub);
			}
		});
		
		vlayout.addComponent(open1);
		
		Button open2 = new Button("Open Sub-Window with Modal");
		open2.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				Image image = new Image(null, new ThemeResource("../vaadinstudy/images/Tulips.jpg"));
				image.setSizeUndefined();
					
				MySub sub = new MySub();
				sub.setHeight("200px");
				sub.setWidth("400px");
				
				sub.setPositionX(200);
				sub.setPositionY(50);
				
				sub.setModal(true);
				sub.setClosable(true);
				sub.setContent(image);
				//UI.getCurrent().addWindow(sub); //same as below
				addWindow(sub);
			}
		});
		
		vlayout.addComponent(open2);
		initSpace();
	}
	
	
	private void initHorizontalSpilitPanel(){
		Panel panel = new Panel("Split Panels Inside This Panel");
		panel.setWidth("50%");
		HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
		hsplit.setLocked(true);
		panel.setContent(hsplit);
		
		
		// dummy data source
		final Object[][] planets = new Object[][]{
			new Object[]{"Mercury"},
			new Object[]{"Venus"},
			new Object[]{"Earth","The Moon"},
			new Object[]{"Mars","Phobos","Deimos"},
			new Object[]{"Jupiter","Io","Europa","Ganymedes","Callisto"}
		};
		
		final Tree tree = new Tree("The planets and The major moons");
		
		for(int i=0;i<planets.length;i++){
			String planet = (String)(planets[i][0]);
			tree.addItem(planet);
			
			if(planets[i].length == 1)
				// The planet has no moons so make it a leaf
				tree.setChildrenAllowed(planet, false);
			else{
				//Add children(moons) under the planet
				for(int j=1;j<planets[i].length;j++){ // j=0 is trap, note!!!
					String moon = (String)planets[i][j];
					tree.addItem(moon);
					tree.setParent(moon, planet);
					tree.setChildrenAllowed(moon, false);
				}
				
				// Expand the subtree
				tree.expandItemsRecursively(planet);
			}
		}
		hsplit.setFirstComponent(tree);
		
		VerticalSplitPanel vsplit = new VerticalSplitPanel();
		hsplit.setSecondComponent(vsplit);
		vsplit.addComponent(new Label("Here's the upper panel"));
		vsplit.addComponent(new Label("Here's the lower panel"));
		
		vlayout.addComponent(panel);
		
		initSpace();
	}
	
	private void initJavaScript(){
	    //Page.getCurrent().getJavaScript().execute("alert ('Hello')");
	    //JavaScript.getCurrent().execute("alert ('Hello')");
	    
	    JavaScript.getCurrent().addFunction("com.debuglife.vaadinstudy.myfunc", new JavaScriptFunction(){
            @Override
            public void call(JSONArray arguments) throws JSONException {
                try {
                    String message = arguments.getString(0);
                    int value = arguments.getInt(1);
                    Notification.show("Message: " + message + ", value: " + value, Type.HUMANIZED_MESSAGE);
                } catch(Exception e) {
                    Notification.show("Error: " + e.getMessage(), Type.HUMANIZED_MESSAGE);
                }
                
            }
	    });
	    Link link = new Link("Send Message", new ExternalResource("javascript:com.debuglife.vaadinstudy.myfunc(prompt('Message'), 42)"));
	    
	    vlayout.addComponent(link);
	    initSpace();
	}
	
	private void initJavaScriptComponent(){
//	    VerticalLayout h = new VerticalLayout();
//	    h.setSizeFull();
//	    
//	    final MyComponent mycom = new MyComponent();
//	    String option = "{title:{text:'未来一周气温变化',subtext:'纯属虚构'},tooltip:{trigger:'axis'},legend:{data:['最高气温','最低气温']},toolbox:{show:true,feature:{mark:{show:true},dataView:{show:true,readOnly:false},magicType:{show:true,type:['line','bar','stack','tiled']},restore:{show:true},saveAsImage:{show:true}}},calculable:true,xAxis:[{type:'category',boundaryGap:false,data:['周一','周二','周三','周四','周五','周六','周日']}],yAxis:[{type:'value',axisLabel:{formatter:'{value} °C'}}],series:[{name:'最高气温',type:'line',data:[11,11,15,13,12,13,10],markPoint:{data:[{type:'max',name:'最大值'},{type:'min',name:'最小值'}]},markLine:{data:[{type:'average',name:'平均值'}]}},{name:'最低气温',type:'line',data:[1,-2,2,5,3,2,0],markPoint:{data:[{name:'周最低',value:-2,xAxis:1,yAxis:-1.5}]},markLine:{data:[{type:'average',name:'平均值'}]}}]}";
//	    mycom.setSizeFull();
//	    mycom.setValue(option);
//	    h.addComponent(mycom);
//	    
//	    vlayout.addComponent(h);
//	    initSpace();
	}
}






