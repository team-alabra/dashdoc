using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Dashdoc.API.Data.EntityConfigurations;

public class AgencyConfiguration: IEntityTypeConfiguration<Agency>
{
    public void Configure(EntityTypeBuilder<Agency> builder)
    {
        builder.HasKey(e => e.Id);
        
        builder.Property(e => e.Name).IsRequired();
        builder.Property(e => e.Email).IsRequired();
    }
}