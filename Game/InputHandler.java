import java.util.Vector;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

/**
 * Class used to handle user input.
 */
public class InputHandler
{
    // Add key toggles.

    public enum KeyType
    {
        M,
        W,
        A,
        S,
        D,
        LEFT,
        RIGHT,
        DOWN,
        MBLEFT,
        NONE,
        E,
        P,
        O,
        ESC,
        U
    }

    class GameButton
    {
        private KeyType key;
        private boolean toggled = false;
        private boolean state = false;
        private boolean press = false;
    
        public GameButton(KeyType key) { this.key = key; }
        public KeyType getKey() { return this.key; }
        public boolean isDown() { return this.state; }
        public boolean isToggled() { return this.toggled; }
        public boolean getPress() { return this.press; }
    
        public void update(boolean state)
        {
            // If there's been a state change.
            if (this.state != state)
            {
                this.press = state;
                this.state = state;
                if (!toggled && state) toggled = true;
                else if (toggled && state) toggled = false;
            }
            else
            {
                this.press = false;
            }
        }
    }
    /**
     * The game scene.
     */
    private Game scene;

    /**
     * Contains a mapping to all of the game buttons.
     */
    private Vector<GameButton> buttons = new Vector<GameButton>();

    public InputHandler(Game scene)
    {
        this.scene = scene;
        this.buttons.add(new GameButton(KeyType.W));
        this.buttons.add(new GameButton(KeyType.A));
        this.buttons.add(new GameButton(KeyType.S));
        this.buttons.add(new GameButton(KeyType.D));
        this.buttons.add(new GameButton(KeyType.LEFT));
        this.buttons.add(new GameButton(KeyType.RIGHT));
        this.buttons.add(new GameButton(KeyType.DOWN));
        this.buttons.add(new GameButton(KeyType.MBLEFT));
        this.buttons.add(new GameButton(KeyType.M));
        this.buttons.add(new GameButton(KeyType.E));
        this.buttons.add(new GameButton(KeyType.P));
        this.buttons.add(new GameButton(KeyType.O));
        this.buttons.add(new GameButton(KeyType.ESC));
        this.buttons.add(new GameButton(KeyType.U));
    }

    public GameButton getKeyState(KeyType key)
    {
        for (GameButton button : buttons)
            if (button.getKey() == key)
                return button;
        return new GameButton(KeyType.NONE);
    }

    /**
     * Updates the states of all the keys.
     */
    public void update()
    {
        updateKeyStates();
        getInput();
    }

    /**
     * Updates the states of all the keys.
     */
    private void updateKeyStates()
    {
        getKeyState(KeyType.M).update(Keyboard.isKeyPressed(Keyboard.Key.M));
        getKeyState(KeyType.W).update(Keyboard.isKeyPressed(Keyboard.Key.W));
        getKeyState(KeyType.A).update(Keyboard.isKeyPressed(Keyboard.Key.A));
        getKeyState(KeyType.S).update(Keyboard.isKeyPressed(Keyboard.Key.S));
        getKeyState(KeyType.D).update(Keyboard.isKeyPressed(Keyboard.Key.D));
        getKeyState(KeyType.LEFT).update(Keyboard.isKeyPressed(Keyboard.Key.LEFT));
        getKeyState(KeyType.RIGHT).update(Keyboard.isKeyPressed(Keyboard.Key.RIGHT));
        getKeyState(KeyType.DOWN).update(Keyboard.isKeyPressed(Keyboard.Key.DOWN));
        getKeyState(KeyType.E).update(Keyboard.isKeyPressed(Keyboard.Key.E));
        getKeyState(KeyType.P).update(Keyboard.isKeyPressed(Keyboard.Key.P));
        getKeyState(KeyType.O).update(Keyboard.isKeyPressed(Keyboard.Key.O));
        getKeyState(KeyType.ESC).update(Keyboard.isKeyPressed(Keyboard.Key.ESCAPE));
        getKeyState(KeyType.U).update(Keyboard.isKeyPressed(Keyboard.Key.U));
    }
    private void getInput()
    {
        getKeyState(KeyType.MBLEFT).update(getKeyState(KeyType.MBLEFT).isDown());
        // Iterates through all of the user input.
        for(Event event : scene.pollEvents())
        {
            // Handles the event.
            switch(event.type)
            {
                // Closes the game window if X is pressed.
                case CLOSED: scene.close(); break;
                // Handles the user input.
                case MOUSE_BUTTON_PRESSED:
                case MOUSE_BUTTON_RELEASED: inputHandler(event.asMouseButtonEvent().button); break;
                // default: inputHandler(Mouse.Button.XBUTTON1);
            }
        }
    }

    private void inputHandler(Mouse.Button mButton)
    {
        // Flip the state.
        if (mButton == Mouse.Button.LEFT) getKeyState(KeyType.MBLEFT).update(!getKeyState(KeyType.MBLEFT).isDown());
        
    }
}