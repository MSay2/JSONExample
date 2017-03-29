package fr.example.json;

public class ItemData
{
	private String image_url, title, text;
	
	// public constructor
	public ItemData()
	{ }
	// end public constructor
	
	
	// Getter
	public String getImageUrl()
	{
		return image_url;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getText()
	{
		return text;
	}
	// end Getter
	
	
	// Setter
	public void setImageUrl(String image_url)
	{
		this.image_url = image_url;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	// end Setter
}
