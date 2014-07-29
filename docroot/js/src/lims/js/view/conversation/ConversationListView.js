/**
 * Conversation View
 *
 * The class extends Y.View. It represents the view for a single conversation.
 */
Y.namespace('LIMS.View');

Y.LIMS.View.ConversationListView = Y.Base.create('conversationListView', Y.View, [], {

    // Specify an optional model to associate with the view.
    model: Y.LIMS.Model.MessageListModel,

    initializer: function () {
        // Attach events
        this._attachEvents();

        return this;
    },

    /**
     * Updates timestamp in each conversations
     */
    updateTimestamps: function () {
        // Vars
        var index,
            conversationItemViews = this.get('conversationItemViews'),
            conversationItem;

        for (index = 0; index < conversationItemViews.length; index++) {
            conversationItem = conversationItemViews[index];
            conversationItem.updateTimestamp();
        }
    },

    /**
     * Attaches listener to elements
     *
     * @private
     */
    _attachEvents: function () {
        // Vars
        var messageTextField = this.get('messageTextField'),
            model = this.get('model');

        // Attach events to text field
        if (messageTextField) {
            messageTextField.on('keydown', this._onMessageTextFieldUpdated, this);
            messageTextField.on('focus', this._onMessageTextFieldUpdated, this);
        }

        // Attach events to model
        model.after('messageAdded', this._onMessageAdded, this);
        model.after('messagesUpdated', this._onMessagesUpdated, this);
    },

    /**
     * Called when a single message is added to the model
     *
     * @param message
     * @private
     */
    _onMessageAdded: function (message) {
        // Add a single message to the list
        this._addMessage(message);
        // Scroll to the last message
        this._scrollToBottom();
    },

    /**
     * Called when the whole message list is updated
     *
     * @param messageList
     * @private
     */
    _onMessagesUpdated: function (messageList) {
        // Hide indicator if it wasn't already hidden
        this.get('activityIndicator').hide();
        // Render the list
        this._renderMessagesList(messageList);
        // Scroll to the last message
        this._scrollToBottom();
    },

    /**
     * Renders and adds a single message to the list
     *
     * @param message
     * @private
     */
    _addMessage: function (message) {

        // Vars
        var conversation,                                           // Newly created conversation
            panelContentList = this.get('panelContentList'),        // The place where are messages will be rendered to
            conversationList = this.get('conversationItemViews');   // List of conversation views

        // New conversation item
        conversation = new Y.LIMS.View.ConversationItemView({model: message});
        conversationList.push(conversation);
        // Render it
        conversation.render();
        // Append to list
        panelContentList.append(conversation.get('container'));
    },

    /**
     * Renders the whole message list
     *
     * @param messageList
     * @private
     */
    _renderMessagesList: function (messageList) {

        // Vars
        var instance = this; // Store the instance

        // Reset content
        this._resetListView();

        // Create view for each message
        messageList.each(function (message) {
            instance._addMessage(message, false);
        });
    },

    /**
     * Removes the whole content from  list view
     *
     * @private
     */
    _resetListView: function () {
        // Vars
        var panelContentList = this.get('panelContentList');
        // This will reset the content of conversation item view
        panelContentList.set('innerHTML', '');
        this.set('conversationItemViews', []);
    },

    /**
     * Scrolls to the last message
     * @private
     */
    _scrollToBottom: function () {
        var panelContent = this.get('panelContent');
        setTimeout(function () {
            panelContent.set('scrollTop', panelContent.get('scrollHeight'));
        }, 1);
    },

    /**
     * Called whenever the message field is updated
     *
     * @param event
     * @private
     */
    _onMessageTextFieldUpdated: function (event) {
        var textField = this.get('messageTextField'),
            value = textField.get('value').replace(/\n|\r/gim, ''); // Get rid of new line characters

        // Send message on enter
        if (event.keyCode === 13 && !event.shiftKey && value.length) {
            event.preventDefault();
            // Empty text field
            textField.set('value', '');
            // Fire an event that message was submitted
            this.fire('messageSubmitted', {
                message: value
            });
        }
    }
}, {

    // Specify attributes and static properties for your View here.
    ATTRS: {
        // Override the default container attribute.
        container: {
            value: null
        },

        // Conversation model
        model: {
            value: null // Y.LIMS.Model.ConversationModel
        },

        // Holds all conversation item views
        conversationItemViews: {
            value: []
        },

        // Panel content container
        panelContent: {
            valueFn: function () {
                var container = this.get('container').one('.panel-content');
                if (container) {
                    return container;
                }
            }
        },

        // List that hold messages
        panelContentList: {
            valueFn: function () {
                var container = this.get('container').one('.panel-content ul');
                if (container) {
                    return container;
                }
            }
        },

        // Container for activity indicator
        activityIndicator: {
            valueFn: function () {
                var container = this.get('container').one('.preloader');
                if (container) {
                    return container;
                }
            }
        },

        // Message text field container
        messageTextField: {
            valueFn: function () {
                var container = this.get('container').one('.panel-input textarea');
                if (container) {
                    return container;
                }
            }
        }

    }

});

