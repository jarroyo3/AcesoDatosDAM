package menu;

public class ExitCommand extends Command{

	private boolean closed;
	
	protected ExitCommand() {
		super("Salir");
		this.reset();
	}

	@Override
	public void execute() {
		this.closed = true;
	}
	
	public boolean isClosed() {
		return this.closed;
	}
	
	public void reset() {
		this.closed = false;
	}
}
