namespace Dashdoc.API.Infrastructure.Utils;

// TODO - fix styling and add templates to DB
// class is no longer needed once EmailTemplateRepository is created.
public static class EmailContentBuilder
{
    public static string Build(string name)
    {
      var messageContent = $@"
        <html>
          <head>
            <style>
              .container {{
                width: 400px;
                display: flex;
                flex-direction: column;
                align-items: center;
              }}
              .content {{
                font-family: Arial;
                border: 1px solid lightgrey;
                width: 300px;
                height: 500px;
                padding: 1rem;
              }}
              .top-logo {{
                font-size: 32px;
                margin-bottom: 1rem;
              }}
              footer {{
                margin-top: 3rem;
              }}
            </style>
          </head>
          <body>
              <div class=""container"">
                <div class=""top-logo"">Dashdoc</div>
                <div class=""content"">
                  <p> Hey {name},</p>
                  <p>Your verification code is: 12345</p>
                </div>
                <footer> 2024 Dashdoc LLC. All rights reserved. </footer>
              </div>
          </body>
        </html>
      ";

        return messageContent;
    }
}